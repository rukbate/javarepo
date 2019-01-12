package com.scb.app.instrument.model;

import com.scb.app.instrument.InstrumentType;

import java.time.LocalDate;

public class PrimeInstrument extends Instrument {

    private final String exchangeCode;

    public PrimeInstrument(String code, LocalDate lastTradingDate, LocalDate deliveryDate, String market, String label, String exchangeCode, boolean tradable) {
        super(InstrumentType.PRIME, code, lastTradingDate, deliveryDate, market, label, tradable);
        this.exchangeCode = exchangeCode;
    }

    @Override
    public boolean match(String code) {
        return this.exchangeCode.equals(code);
    }

    public String getExchangeCode() {
        return exchangeCode;
    }
}