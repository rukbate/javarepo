package com.scb.app.instrument.model;

import com.scb.app.instrument.InstrumentType;

import java.time.LocalDate;

public class LmeInstrument extends Instrument {

    public LmeInstrument(String code, LocalDate lastTradingDate, LocalDate deliveryDate, String market, String label) {
        super(InstrumentType.LME, code, lastTradingDate, deliveryDate, market, label, true);
    }

    @Override
    public boolean match(String code) {
        return this.getCode().equals(code);
    }
}
