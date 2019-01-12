package com.scb.app.rule;

import com.scb.app.instrument.builder.InstrumentBuilder;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.model.LmeInstrument;
import com.scb.app.instrument.model.PrimeInstrument;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

public abstract class RuleTestBase {

    @Mock
    InstrumentBuilder builder;

    Instrument lmeInstrument;

    Instrument primeInstrument;

    public void setUp() {
        MockitoAnnotations.initMocks(this);

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
    }
}
