package com.scb.app.rule;

import com.scb.app.instrument.builder.InstrumentBuilder;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.InstrumentFields;

import java.util.List;

public class DefaultRule implements Rule {

    @Override
    public void apply(String exchange, List<Instrument> components, InstrumentBuilder builder) {
        Instrument targetInstrument = null;

        for(Instrument instrument: components) {
            if (instrument.getType().name().equals(exchange)) {
                targetInstrument = instrument;
            }
        }

        if(targetInstrument != null) {
            if (!builder.hasLastTradingDate()) {
                builder.withLastTradingDate(targetInstrument.getValue(InstrumentFields.LAST_TRADING_DATE));
            }
            if (!builder.hasDeliveryDate()) {
                builder.withDeliveryDate(targetInstrument.getValue(InstrumentFields.DELIVERY_DATE));
            }
            if (!builder.hasMarket()) {
                builder.withMarket(targetInstrument.getValue(InstrumentFields.MARKET));
            }
            if (!builder.hasLabel()) {
                builder.withLabel(targetInstrument.getValue(InstrumentFields.LABEL));
            }
            if (!builder.hasTradable()) {
                builder.withTradable(targetInstrument.getValueOrDefault(InstrumentFields.TRADABLE, "TRUE"));
            }
        }
    }
}
