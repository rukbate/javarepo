package com.scb.app.service;

import com.scb.app.exception.InstrumentException;
import com.scb.app.exception.MissingFieldException;
import com.scb.app.exception.NoMatchedInstrumentException;
import com.scb.app.exception.UnknownExchangeException;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.InstrumentType;
import com.scb.app.instrument.model.LmeInstrument;
import com.scb.app.instrument.model.PrimeInstrument;
import com.scb.app.rule.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class AggregateServiceTest {

    private InstrumentService engine;

    private List<Instrument> instruments;

    private List<Rule> rules;

    private Rule defaultRule;

    @Before
    public void setUp() {
        Instrument lmeInstrument = new LmeInstrument("PB_03_2018",
                LocalDate.of(2018, 3, 15),
                LocalDate.of(2018, 3, 17),
                "PB_LME",
                "Lead 13 March 2018");

        Instrument primeInstrument = new PrimeInstrument("PRIME_PB_03_2018",
                LocalDate.of(2018, 3, 14),
                LocalDate.of(2018, 3, 18),
                "PB_PRIME",
                "Lead 13 March 2018",
                "PB_03_2018",
                false);

        instruments = new ArrayList<>(8);
        instruments.add(lmeInstrument);
        instruments.add(primeInstrument);

        Rule lastTradingDateAndDeliveryDateRule = new LastTradingDateAndDeliveryDateRule();
        Rule tradableRule = new TradableRule();
        Rule marketRule = new MarketRule();
        defaultRule = new DefaultRule();

        rules = new LinkedList<>();
        rules.add(lastTradingDateAndDeliveryDateRule);
        rules.add(tradableRule);
        rules.add(marketRule);

        engine = new AggregateService(rules, instruments);
    }

    @Test(expected = UnknownExchangeException.class)
    public void should_throw_exception_for_unknown_exchange() throws InstrumentException {
        engine.publish("UNKNOWN", "PB_03_2018");
    }

    @Test(expected = UnknownExchangeException.class)
    public void should_throw_exception_for_standard_exchange() throws InstrumentException {
        engine.publish("STANDARD", "PB_03_2018");
    }

    @Test(expected = NoMatchedInstrumentException.class)
    public void should_throw_exception_for_no_matched_instrument() throws InstrumentException {
        engine.publish("LME", "DUMMY_CODE");
    }

    @Test(expected = MissingFieldException.class)
    public void should_throw_exception_if_missing_rule() throws InstrumentException {
        engine.publish("LME", "PB_03_2018");
    }

    @Test
    public void should_set_fields_correctly_when_lme_publishes() throws InstrumentException {
        engine.addRule(defaultRule);

        Instrument instrument = engine.publish("LME", "PB_03_2018");
        assertEquals(InstrumentType.STANDARD, instrument.getType());
        assertEquals("PB_03_2018", instrument.getCode());
        assertEquals(LocalDate.of(2018, 3, 15), instrument.getLastTradingDate());
        assertEquals(LocalDate.of(2018, 3, 17), instrument.getDeliveryDate());
        assertEquals("LME", instrument.getMarket());
        assertEquals("Lead 13 March 2018", instrument.getLabel());
        assertTrue(instrument.isTradable());
    }

    @Test
    public void should_set_fields_correctly_when_prime_publishes() throws InstrumentException {
        engine.addRule(defaultRule);
        Instrument instrument = engine.publish("PRIME", "PB_03_2018");
        assertEquals(InstrumentType.STANDARD, instrument.getType());
        assertEquals("PB_03_2018", instrument.getCode());
        assertEquals(LocalDate.of(2018, 3, 15), instrument.getLastTradingDate());
        assertEquals(LocalDate.of(2018, 3, 17), instrument.getDeliveryDate());
        assertEquals("PRIME", instrument.getMarket());
        assertEquals("Lead 13 March 2018", instrument.getLabel());
        assertFalse(instrument.isTradable());
    }
}