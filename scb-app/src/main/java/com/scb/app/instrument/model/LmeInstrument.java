package com.scb.app.instrument.model;

import com.scb.app.instrument.InstrumentType;

import java.util.Map;

public class LmeInstrument extends Instrument {

    public LmeInstrument(Map<String, String> fields) {
        super(InstrumentType.LME, fields);
    }
}
