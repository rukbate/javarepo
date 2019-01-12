package com.scb.app.instrument.builder;

import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.model.LmeInstrument;

public class LmeInstrumentBuilder extends InstrumentBuilder {

    @Override
    boolean validate() {
        return this.hasCode()
                && this.hasLastTradingDate()
                && this.hasDeliveryDate()
                && this.hasMarket()
                && this.hasLabel();
    }

    @Override
    Instrument buildInstrument() {
        return new LmeInstrument(this.getCode(), this.getLastTradingDate(), this.getDeliveryDate(), this.getMarket(), this.getLabel());
    }
}
