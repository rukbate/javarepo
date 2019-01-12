package com.scb.app.rule;

import com.scb.app.instrument.builder.InstrumentBuilder;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.model.LmeInstrument;
import com.scb.app.instrument.model.PrimeInstrument;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

public abstract class RuleTestBase {

    @Mock
    InstrumentBuilder builder;

    Instrument lmeInstrument;

    Instrument primeInstrument;

    public void setUp() {
        MockitoAnnotations.initMocks(this);
        lmeInstrument = new LmeInstrument("PB_03_2018",
                LocalDate.of(2018, 3, 15),
                LocalDate.of(2018, 3, 17),
                "PB_LME",
                "Lead 13 March 2018");

        primeInstrument = new PrimeInstrument("PRIME_PB_03_2018",
                LocalDate.of(2018, 3, 14),
                LocalDate.of(2018, 3, 18),
                "PB_PRIME",
                "Lead 13 March 2018",
                "PB_03_2018",
                false);
    }
}
