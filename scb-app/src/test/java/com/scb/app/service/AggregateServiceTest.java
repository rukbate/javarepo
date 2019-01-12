package com.scb.app.service;

import com.scb.app.exception.InstrumentException;
import com.scb.app.exception.MissingFieldException;
import com.scb.app.exception.NoMatchedInstrumentException;
import com.scb.app.exception.UnknownExchangeException;
import com.scb.app.instrument.InstrumentType;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.model.LmeInstrument;
import com.scb.app.instrument.model.PrimeInstrument;
import com.scb.app.rule.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class AggregateServiceTest {

    private InstrumentService engine;

    private Rule defaultRule;

    @Before
    public void setUp() {
        Map<String, String> lmeFields = new HashMap<>(8);
        lmeFields.put(InstrumentFields.CODE, "PB_03_2018");
        lmeFields.put(InstrumentFields.LAST_TRADING_DATE, "15-03-2018");
        lmeFields.put(InstrumentFields.DELIVERY_DATE, "17-03-2018");
        lmeFields.put(InstrumentFields.MARKET, "PB_LME");
        lmeFields.put(InstrumentFields.LABEL, "Lead 13 March 2018");
        Instrument lmeInstrument = new LmeInstrument(lmeFields);

        Map<String, String> primeFields = new HashMap<>(8);
        primeFields.put(InstrumentFields.CODE, "PRIME_PB_03_2018");
        primeFields.put(InstrumentFields.LAST_TRADING_DATE, "14-03-2018");
        primeFields.put(InstrumentFields.DELIVERY_DATE, "18-03-2018");
        primeFields.put(InstrumentFields.MARKET, "PB_PRIME");
        primeFields.put(InstrumentFields.LABEL, "Lead 13 March 2018");
        primeFields.put(InstrumentFields.EXCHANGE_CODE, "PB_03_2018");
        primeFields.put(InstrumentFields.TRADABLE, "FALSE");
        Instrument primeInstrument = new PrimeInstrument(primeFields);

        List<Instrument> instruments = new ArrayList<>(8);
        instruments.add(lmeInstrument);
        instruments.add(primeInstrument);

        List<Rule> rules = new LinkedList<>();
        rules.add(new LastTradingDateAndDeliveryDateRule());
        rules.add(new TradableRule());
        rules.add(new MarketRule());

        defaultRule = new DefaultRule();
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
        assertEquals("PB_03_2018", instrument.getValue(InstrumentFields.CODE));
        assertEquals("15-03-2018", instrument.getValue(InstrumentFields.LAST_TRADING_DATE));
        assertEquals("17-03-2018", instrument.getValue(InstrumentFields.DELIVERY_DATE));
        assertEquals("LME", instrument.getValue(InstrumentFields.MARKET));
        assertEquals("Lead 13 March 2018", instrument.getValue(InstrumentFields.LABEL));
        assertEquals("TRUE", instrument.getValue(InstrumentFields.TRADABLE));
    }

    @Test
    public void should_set_fields_correctly_when_prime_publishes() throws InstrumentException {
        engine.addRule(defaultRule);

        Instrument instrument = engine.publish("PRIME", "PB_03_2018");
        assertEquals(InstrumentType.STANDARD, instrument.getType());
        assertEquals("PB_03_2018", instrument.getValue(InstrumentFields.CODE));
        assertEquals("15-03-2018", instrument.getValue(InstrumentFields.LAST_TRADING_DATE));
        assertEquals("17-03-2018", instrument.getValue(InstrumentFields.DELIVERY_DATE));
        assertEquals("PRIME", instrument.getValue(InstrumentFields.MARKET));
        assertEquals("Lead 13 March 2018", instrument.getValue(InstrumentFields.LABEL));
        assertEquals("FALSE", instrument.getValue(InstrumentFields.TRADABLE));
    }
}