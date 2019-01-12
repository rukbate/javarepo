package com.scb.app.instrument.model;

import com.scb.app.instrument.InstrumentType;

import java.time.LocalDate;

public abstract class Instrument {
    private final InstrumentType type;
    private final String code;
    private final LocalDate lastTradingDate;
    private final LocalDate deliveryDate;
    private final String market;
    private final String label;
    private final boolean tradable;

    public Instrument(InstrumentType type, String code, LocalDate lastTradingDate, LocalDate deliveryDate, String market, String label, boolean tradable) {
        this.type = type;
        this.code = code;
        this.lastTradingDate = lastTradingDate;
        this.deliveryDate = deliveryDate;
        this.market = market;
        this.label = label;
        this.tradable = tradable;
    }

    public boolean match(String code) {
        return this.getMappingKey().equals(code);
    }

    abstract String getMappingKey();

    public InstrumentType getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public LocalDate getLastTradingDate() {
        return lastTradingDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public String getMarket() {
        return market;
    }

    public String getLabel() {
        return label;
    }

    public boolean isTradable() {
        return tradable;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "lastTradingDate=" + lastTradingDate +
                ", deliveryDate=" + deliveryDate +
                ", market='" + market + '\'' +
                ", label='" + label + '\'' +
                ", tradable=" + tradable +
                '}';
    }
}
