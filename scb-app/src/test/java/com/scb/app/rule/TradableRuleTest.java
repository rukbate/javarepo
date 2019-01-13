package com.scb.app.rule;

import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.model.Instrument;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class TradableRuleTest extends RuleTestBase {

    private TradableRule rule;

    @Before
    public void setUp() {
        super.setUp();

        rule = new TradableRule();
    }

    @Test
    public void should_set_tradable_as_true_for_lme_instrument() {
        List<Instrument> instruments = new ArrayList<>(8);
        instruments.add(primeInstrument);

        rule.apply(lmeInstrument, instruments, builder);

        verify(builder).withField(eq(InstrumentFields.TRADABLE), eq("TRUE"));
    }

    @Test
    public void should_set_tradable_from_input_instrument() {
        List<Instrument> instruments = new ArrayList<>(8);
        instruments.add(lmeInstrument);

        rule.apply(primeInstrument, instruments, builder);

        verify(builder).withField(eq(InstrumentFields.TRADABLE), eq("FALSE"));
    }
}