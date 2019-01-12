package com.scb.app.instrument.model;

import com.scb.app.instrument.InstrumentType;

import java.util.Map;

public class StandardInstrument extends Instrument {

    public StandardInstrument(Map<String, String> fields) {
        super(InstrumentType.STANDARD, fields);
    }
}
