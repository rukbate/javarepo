package com.scb.app.instrument.builder;

import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.model.StandardInstrument;

public class StandardInstrumentBuilder extends InstrumentBuilder {

    @Override
    boolean validate() {
        return this.hasCode()
                && this.hasLastTradingDate()
                && this.hasDeliveryDate()
                && this.hasMarket()
                && this.hasLabel()
                && this.hasTradable();
    }

    @Override
    Instrument buildInstrument() {
        return new StandardInstrument(this.getCode(), this.getLastTradingDate(), this.getDeliveryDate(), this.getMarket(), this.getLabel(), this.getTradable());
    }
}
