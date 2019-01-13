package com.scb.app.service;

import com.scb.app.exception.InstrumentException;
import com.scb.app.exception.MissingFieldException;
import com.scb.app.exception.UnknownExchangeException;
import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.InstrumentType;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.model.LmeInstrument;
import com.scb.app.instrument.model.PrimeInstrument;
import com.scb.app.instrument.model.StandardInstrument;
import com.scb.app.rule.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class AggregateServiceTest {

    private InstrumentService engine;

    private Rule defaultRule;

    private Instrument lmeInstrument;

    private Instrument primeInstrument;

    private Instrument standardInstrument;

    @Before
    public void setUp() {
        Map<String, String> lmeFields = new HashMap<>(8);
        lmeFields.put(InstrumentFields.CODE, "PB_03_2018");
        lmeFields.put(InstrumentFields.LAST_TRADING_DATE, "15-03-2018");
        lmeFields.put(InstrumentFields.DELIVERY_DATE, "17-03-2018");
        lmeFields.put(InstrumentFields.MARKET, "PB_LME");
        lmeFields.put(InstrumentFields.LABEL, "Lead 13 March 2018");
        lmeInstrument = new LmeInstrument(lmeFields);

        Map<String, String> primeFields = new HashMap<>(8);
        primeFields.put(InstrumentFields.CODE, "PRIME_PB_03_2018");
        primeFields.put(InstrumentFields.LAST_TRADING_DATE, "14-03-2018");
        primeFields.put(InstrumentFields.DELIVERY_DATE, "18-03-2018");
        primeFields.put(InstrumentFields.MARKET, "PB_PRIME");
        primeFields.put(InstrumentFields.LABEL, "Lead 13 March 2018");
        primeFields.put(InstrumentFields.EXCHANGE_CODE, "PB_03_2018");
        primeFields.put(InstrumentFields.TRADABLE, "FALSE");
        primeInstrument = new PrimeInstrument(primeFields);

        Map<String, String> standardFields = new HashMap<>(8);
        standardFields.put(InstrumentFields.CODE, "PB_03_2018");
        standardFields.put(InstrumentFields.LAST_TRADING_DATE, "15-03-2018");
        standardFields.put(InstrumentFields.DELIVERY_DATE, "17-03-2018");
        standardFields.put(InstrumentFields.MARKET, "PB_LME");
        standardFields.put(InstrumentFields.LABEL, "Lead 13 March 2018");
        standardInstrument = new StandardInstrument(standardFields);

        List<Rule> rules = new LinkedList<>();
        rules.add(new LastTradingDateAndDeliveryDateRule());
        rules.add(new TradableRule());
        rules.add(new MarketRule());

        defaultRule = new DefaultRule();
        engine = new AggregateService(rules);
    }

    @Test(expected = UnknownExchangeException.class)
    public void should_throw_exception_for_standard_exchange() throws InstrumentException {
        engine.publish(standardInstrument);
    }

    @Test(expected = MissingFieldException.class)
    public void should_throw_exception_if_missing_rule() throws InstrumentException {
        engine.publish(lmeInstrument);
    }

    @Test
    public void should_set_fields_correctly_when_lme_publishes() throws InstrumentException {
        engine.addRule(defaultRule);

        Instrument instrument = engine.publish(lmeInstrument);
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

        engine.publish(lmeInstrument);
        Instrument instrument = engine.publish(primeInstrument);
        assertEquals(InstrumentType.STANDARD, instrument.getType());
        assertEquals("PB_03_2018", instrument.getValue(InstrumentFields.CODE));
        assertEquals("15-03-2018", instrument.getValue(InstrumentFields.LAST_TRADING_DATE));
        assertEquals("17-03-2018", instrument.getValue(InstrumentFields.DELIVERY_DATE));
        assertEquals("PRIME", instrument.getValue(InstrumentFields.MARKET));
        assertEquals("Lead 13 March 2018", instrument.getValue(InstrumentFields.LABEL));
        assertEquals("FALSE", instrument.getValue(InstrumentFields.TRADABLE));
    }
}