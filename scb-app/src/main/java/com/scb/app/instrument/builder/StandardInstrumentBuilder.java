package com.scb.app.instrument.builder;

import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.model.StandardInstrument;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> fields = new HashMap<>(8);
        fields.put(InstrumentFields.CODE, this.getCode());
        fields.put(InstrumentFields.LAST_TRADING_DATE, this.getLastTradingDate());
        fields.put(InstrumentFields.DELIVERY_DATE, this.getDeliveryDate());
        fields.put(InstrumentFields.MARKET, this.getMarket());
        fields.put(InstrumentFields.LABEL, this.getLabel());
        fields.put(InstrumentFields.TRADABLE, this.getTradable());

        return new StandardInstrument(fields);
    }
}
