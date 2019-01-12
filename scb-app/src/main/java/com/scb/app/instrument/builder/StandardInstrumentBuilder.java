package com.scb.app.instrument.builder;

import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.model.StandardInstrument;

public class StandardInstrumentBuilder extends InstrumentBuilder {

    @Override
    boolean validate() {
        return this.hasField(InstrumentFields.CODE)
                && this.hasField(InstrumentFields.LAST_TRADING_DATE)
                && this.hasField(InstrumentFields.DELIVERY_DATE)
                && this.hasField(InstrumentFields.MARKET)
                && this.hasField(InstrumentFields.LABEL)
                && this.hasField(InstrumentFields.TRADABLE);
    }

    @Override
    Instrument buildInstrument() {
        return new StandardInstrument(this.getFields());
    }
}
