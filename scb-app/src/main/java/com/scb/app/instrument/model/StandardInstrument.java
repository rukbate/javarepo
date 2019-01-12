package com.scb.app.instrument.model;

import com.scb.app.instrument.InstrumentType;

import java.time.LocalDate;

public class StandardInstrument extends Instrument {

    public StandardInstrument(String code, LocalDate lastTradingDate, LocalDate deliveryDate, String market, String label, boolean tradable) {
        super(InstrumentType.STANDARD, code, lastTradingDate, deliveryDate, market, label, tradable);
    }

    @Override
    String getMappingKey() {
        return this.getCode();
    }
}
