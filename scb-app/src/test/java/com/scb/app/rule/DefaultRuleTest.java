package com.scb.app.rule;

import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.model.Instrument;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class DefaultRuleTest extends RuleTestBase {

    private DefaultRule rule;

    private List<Instrument> instruments;

    @Before
    public void setUp() {
        super.setUp();
        rule = new DefaultRule();

        instruments = new ArrayList<>(8);

        when(builder.hasField(InstrumentFields.LAST_TRADING_DATE)).thenReturn(Boolean.TRUE);
        when(builder.hasField(InstrumentFields.DELIVERY_DATE)).thenReturn(Boolean.TRUE);
    }

    @Test
    public void should_set_null_fields_from_input_instrument() {
        rule.apply(primeInstrument, instruments, builder);

        verify(builder).withField(eq(InstrumentFields.MARKET), eq("PB_PRIME"));
        verify(builder).withField(eq(InstrumentFields.LABEL), eq("Lead 13 March 2018"));
        verify(builder).withField(eq(InstrumentFields.TRADABLE), eq("FALSE"));
        verify(builder, times(0)).withField(eq(InstrumentFields.LAST_TRADING_DATE), any());
        verify(builder, times(0)).withField(eq(InstrumentFields.DELIVERY_DATE), any());
    }

    @Test
    public void should_set_tradable_for_lme_instrument() {
        rule.apply(lmeInstrument, instruments, builder);

        verify(builder).withField(eq(InstrumentFields.TRADABLE), eq("TRUE"));
    }
}