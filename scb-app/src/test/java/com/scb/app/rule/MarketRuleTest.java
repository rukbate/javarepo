package com.scb.app.rule;

import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.model.Instrument;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

public class MarketRuleTest extends RuleTestBase {

    private MarketRule rule;

    private List<Instrument> instruments;

    @Before
    public void setUp() {
        super.setUp();
        rule = new MarketRule();

        instruments = new ArrayList<>(8);
        instruments.add(lmeInstrument);
    }

    @Test
    public void test_extract_market() {
        assertNull(rule.extractMarket(null));
        assertEquals("", rule.extractMarket(""));
        assertEquals("PB", rule.extractMarket("LME_PB"));
        assertEquals("PB", rule.extractMarket("PRIME_LME_PB"));
        assertEquals("PB", rule.extractMarket("PB"));
    }

    @Test
    public void should_set_market_from_input_instrument() {
        rule.apply(primeInstrument, instruments, builder);

        verify(builder).withField(eq(InstrumentFields.MARKET), eq("PRIME"));
    }
}