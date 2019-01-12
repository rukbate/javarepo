package com.scb.app.rule;

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
        instruments.add(lmeInstrument);
        instruments.add(primeInstrument);

        when(builder.hasLastTradingDate()).thenReturn(Boolean.TRUE);
        when(builder.hasDeliveryDate()).thenReturn(Boolean.TRUE);
        when(builder.hasTradable()).thenReturn(Boolean.TRUE);
    }

    @Test
    public void should_set_null_fields_from_matching_instrument() {
        rule.apply("PRIME", instruments, builder);

        verify(builder).withMarket(eq("PB_PRIME"));
        verify(builder).withLabel(eq("Lead 13 March 2018"));
        verify(builder, times(0)).withLastTradingDate(any());
        verify(builder, times(0)).withDeliveryDate(any());
        verify(builder, times(0)).withTradable(any());
    }

    @Test
    public void should_set_nothing_if_no_matching_instrument() {
        rule.apply("PB", instruments, builder);

        verifyZeroInteractions(builder);
    }
}