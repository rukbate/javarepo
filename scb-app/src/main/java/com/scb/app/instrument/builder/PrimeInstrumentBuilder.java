package com.scb.app.instrument.builder;

import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.model.PrimeInstrument;

public class PrimeInstrumentBuilder extends InstrumentBuilder {

    @Override
    boolean validate() {
        return this.hasCode()
                && this.hasLastTradingDate()
                && this.hasDeliveryDate()
                && this.hasMarket()
                && this.hasLabel()
                && this.hasExchangeCode()
                && this.hasTradable();
    }

    @Override
    Instrument buildInstrument() {
        return new PrimeInstrument(this.getCode(), this.getLastTradingDate(), this.getDeliveryDate(), this.getMarket(), this.getLabel(), this.getExchangeCode(), this.getTradable());
    }
}
