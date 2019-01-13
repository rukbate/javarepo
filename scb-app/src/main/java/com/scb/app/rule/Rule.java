package com.scb.app.rule;

import com.scb.app.instrument.builder.InstrumentBuilder;
import com.scb.app.instrument.model.Instrument;

import java.util.List;

public interface Rule {

    void apply(Instrument instrument, List<Instrument> existingInstruments, InstrumentBuilder builder);

}
