package com.scb.app.instrument.model;

import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.InstrumentType;

import java.util.Map;

public class PrimeInstrument extends Instrument {

    public PrimeInstrument(Map<String, String> fields) {
        super(InstrumentType.PRIME, fields);
        this.setMappingKey(InstrumentFields.EXCHANGE_CODE);
    }
}
