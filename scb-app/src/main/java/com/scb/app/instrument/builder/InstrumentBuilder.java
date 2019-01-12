package com.scb.app.instrument.builder;

import com.scb.app.exception.InstrumentException;
import com.scb.app.exception.MissingFieldException;
import com.scb.app.instrument.model.Instrument;

public abstract class InstrumentBuilder {

    private String code;
    private String lastTradingDate;
    private String deliveryDate;
    private String market;
    private String label;
    private String exchangeCode;
    private String tradable;

    public Instrument build() throws InstrumentException {
        if(!this.validate()) {
            throw new MissingFieldException();
        }

        return this.buildInstrument();
    }

    abstract boolean validate();

    abstract Instrument buildInstrument();

    public InstrumentBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public InstrumentBuilder withLastTradingDate(String lastTradingDate) {
        this.lastTradingDate = lastTradingDate;
        return this;
    }

    public InstrumentBuilder withDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public InstrumentBuilder withMarket(String market) {
        this.market = market;
        return this;
    }

    public InstrumentBuilder withLabel(String label) {
        this.label = label;
        return this;
    }

    public InstrumentBuilder withExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
        return this;
    }

    public InstrumentBuilder withTradable(String tradable) {
        this.tradable = tradable;
        return this;
    }

    boolean hasCode() {
        return this.code != null;
    }

    public boolean hasLastTradingDate() {
        return this.lastTradingDate != null;
    }

    public boolean hasDeliveryDate() {
        return this.deliveryDate != null;
    }

    public boolean hasMarket() {
        return this.market != null;
    }

    public boolean hasLabel() {
        return this.label != null;
    }

    boolean hasExchangeCode() {
        return this.exchangeCode != null;
    }

    public boolean hasTradable() {
        return this.tradable != null;
    }

    String getCode() {
        return code;
    }

    String getLastTradingDate() {
        return lastTradingDate;
    }

    String getDeliveryDate() {
        return deliveryDate;
    }

    String getMarket() {
        return market;
    }

    String getLabel() {
        return label;
    }

    String getExchangeCode() {
        return exchangeCode;
    }

    String getTradable() {
        return tradable;
    }
}
