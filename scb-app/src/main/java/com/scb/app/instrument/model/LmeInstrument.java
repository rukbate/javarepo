package com.scb.app.instrument.model;

import com.scb.app.instrument.InstrumentType;

import java.time.LocalDate;

public class LmeInstrument extends Instrument {

    public LmeInstrument(String code, LocalDate lastTradingDate, LocalDate deliveryDate, String market, String label) {
        super(InstrumentType.LME, code, lastTradingDate, deliveryDate, market, label, true);
    }

    @Override
    String getMappingKey() {
        return this.getCode();
    }
}
