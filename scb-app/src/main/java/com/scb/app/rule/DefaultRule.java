package com.scb.app.rule;

import com.scb.app.instrument.InstrumentFields;
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
            if (!builder.hasField(InstrumentFields.LAST_TRADING_DATE)) {
                builder.withField(InstrumentFields.LAST_TRADING_DATE, targetInstrument.getValue(InstrumentFields.LAST_TRADING_DATE));
            }
            if (!builder.hasField(InstrumentFields.DELIVERY_DATE)) {
                builder.withField(InstrumentFields.DELIVERY_DATE, targetInstrument.getValue(InstrumentFields.DELIVERY_DATE));
            }
            if (!builder.hasField(InstrumentFields.MARKET)) {
                builder.withField(InstrumentFields.MARKET, targetInstrument.getValue(InstrumentFields.MARKET));
            }
            if (!builder.hasField(InstrumentFields.LABEL)) {
                builder.withField(InstrumentFields.LABEL, targetInstrument.getValue(InstrumentFields.LABEL));
            }
            if (!builder.hasField(InstrumentFields.TRADABLE)) {
                builder.withField(InstrumentFields.TRADABLE, targetInstrument.getValueOrDefault(InstrumentFields.TRADABLE, "TRUE"));
            }
        }
    }
}
