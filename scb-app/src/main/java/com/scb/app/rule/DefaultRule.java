package com.scb.app.rule;

import com.scb.app.instrument.builder.InstrumentBuilder;
import com.scb.app.instrument.model.Instrument;

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
                builder.withLastTradingDate(targetInstrument.getLastTradingDate());
            }
            if (!builder.hasDeliveryDate()) {
                builder.withDeliveryDate(targetInstrument.getDeliveryDate());
            }
            if (!builder.hasMarket()) {
                builder.withMarket(targetInstrument.getMarket());
            }
            if (!builder.hasLabel()) {
                builder.withLabel(targetInstrument.getLabel());
            }
            if (!builder.hasTradable()) {
                builder.withTradable(targetInstrument.isTradable());
            }
        }
    }
}
