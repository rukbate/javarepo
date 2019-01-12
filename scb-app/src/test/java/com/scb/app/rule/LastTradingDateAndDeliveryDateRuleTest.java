package com.scb.app.rule;

import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.model.LmeInstrument;
import com.scb.app.instrument.model.PrimeInstrument;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
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

        verify(builder).withLastTradingDate(LocalDate.of(2018, 3, 14));
        verify(builder).withDeliveryDate(LocalDate.of(2018, 3, 18));
    }

    @Test
    public void should_set_by_lme_instrument_if_both_matching_and_lme_instrument() {
        List<Instrument> instruments = new ArrayList<>(8);
        instruments.add(lmeInstrument);
        instruments.add(primeInstrument);

        rule.apply("PRIME", instruments, builder);

        verify(builder).withLastTradingDate(eq(LocalDate.of(2018, 3, 15)));
        verify(builder).withDeliveryDate(eq(LocalDate.of(2018, 3, 17)));
    }

    @Test
    public void should_set_nothing_if_neither_lme_nor_matching_instrument() {
        List<Instrument> instruments = new ArrayList<>(8);
        instruments.add(primeInstrument);

        rule.apply("LME", instruments, builder);

        verifyZeroInteractions(builder);
    }
}