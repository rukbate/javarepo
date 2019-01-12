package com.scb.app.instrument.builder;

import com.scb.app.exception.InstrumentException;
import com.scb.app.exception.MissingFieldException;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.InstrumentType;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class LmeInstrumentBuilderTest {

    private LmeInstrumentBuilder builder;

    @Before
    public void setUp() {
        builder = new LmeInstrumentBuilder();
    }

    @Test
    public void testValidate() {
        builder.withCode("Code")
                .withLastTradingDate(LocalDate.of(2018, 3, 14))
                .withDeliveryDate(LocalDate.of(2018, 3, 17));

        assertFalse(builder.validate());

        builder.withMarket("PB")
                .withLabel("label");

        assertTrue(builder.validate());
    }

    @Test(expected = MissingFieldException.class)
    public void should_throw_exception_if_missing_field() throws InstrumentException {
        builder.withCode("Code")
                .withLastTradingDate(LocalDate.of(2018, 3, 14))
                .withDeliveryDate(LocalDate.of(2018, 3, 17))
                .build();
    }

    @Test
    public void should_build_instrument() throws InstrumentException {
        Instrument instrument = builder.withCode("Code")
                .withLastTradingDate(LocalDate.of(2018, 3, 14))
                .withDeliveryDate(LocalDate.of(2018, 3, 17))
                .withMarket("PB")
                .withLabel("label")
                .build();

        assertNotNull(instrument);
        assertEquals(InstrumentType.LME, instrument.getType());
    }
}