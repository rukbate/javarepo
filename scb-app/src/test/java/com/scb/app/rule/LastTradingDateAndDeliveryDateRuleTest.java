package com.scb.app.rule;

import com.scb.app.instrument.model.Instrument;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class LastTradingDateAndDeliveryDateRuleTest extends RuleTestBase {

    private LastTradingDateAndDeliveryDateRule rule;

    @Before
    public void setUp() {
        super.setUp();
        rule = new LastTradingDateAndDeliveryDateRule();
    }

    @Test
    public void should_set_by_matching_instrument_if_no_lme_instrument() {
        List<Instrument> instruments = new ArrayList<>(8);
        instruments.add(primeInstrument);

        rule.apply("PRIME", instruments, builder);

        verify(builder).withLastTradingDate("14-03-2018");
        verify(builder).withDeliveryDate("18-03-2018");
    }

    @Test
    public void should_set_by_lme_instrument_if_both_matching_and_lme_instrument() {
        List<Instrument> instruments = new ArrayList<>(8);
        instruments.add(lmeInstrument);
        instruments.add(primeInstrument);

        rule.apply("PRIME", instruments, builder);

        verify(builder).withLastTradingDate(eq("15-03-2018"));
        verify(builder).withDeliveryDate(eq("17-03-2018"));
    }

    @Test
    public void should_set_nothing_if_neither_lme_nor_matching_instrument() {
        List<Instrument> instruments = new ArrayList<>(8);
        instruments.add(primeInstrument);

        rule.apply("LME", instruments, builder);

        verifyZeroInteractions(builder);
    }
}