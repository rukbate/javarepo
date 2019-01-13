package com.scb.app.rule;

import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.builder.InstrumentBuilder;
import com.scb.app.instrument.model.Instrument;

import java.util.List;

public class TradableRule implements Rule {

    @Override
    public void apply(Instrument instrument, List<Instrument> existingInstruments, InstrumentBuilder builder) {
        builder.withField(InstrumentFields.TRADABLE, instrument.getValueOrDefault(InstrumentFields.TRADABLE, "TRUE"));
    }
}
