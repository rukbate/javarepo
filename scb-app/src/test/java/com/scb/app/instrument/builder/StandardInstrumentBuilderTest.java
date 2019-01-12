package com.scb.app.instrument.builder;

import com.scb.app.exception.InstrumentException;
import com.scb.app.exception.MissingFieldException;
import com.scb.app.instrument.InstrumentType;
import com.scb.app.instrument.model.Instrument;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StandardInstrumentBuilderTest {

    private StandardInstrumentBuilder builder;

    @Before
    public void setUp() {
        builder = new StandardInstrumentBuilder();
    }

    @Test
    public void testValidate() {
        builder.withCode("Code")
                .withLastTradingDate("14-03-2018")
                .withDeliveryDate("17-03-2018");

        assertFalse(builder.validate());

        builder.withMarket("PB")
                .withLabel("label")
                .withTradable("FALSE");

        assertTrue(builder.validate());
    }

    @Test(expected = MissingFieldException.class)
    public void should_throw_exception_if_missing_field() throws InstrumentException {
        builder.withCode("Code")
                .withLastTradingDate("14-03-2018")
                .withDeliveryDate("17-03-2018")
                .build();
    }

    @Test
    public void should_build_instrument() throws InstrumentException {
        Instrument instrument = builder.withCode("Code")
                .withLastTradingDate("14-03-2018")
                .withDeliveryDate("17-03-2018")
                .withMarket("PB")
                .withLabel("label")
                .withTradable("FALSE")
                .build();

        assertNotNull(instrument);
        assertEquals(InstrumentType.STANDARD, instrument.getType());
    }
}