package com.scb.app.rule;

import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.builder.InstrumentBuilder;
import com.scb.app.instrument.model.Instrument;

import java.util.List;

public class DefaultRule implements Rule {

    @Override
    public void apply(Instrument instrument, List<Instrument> existingInstruments, InstrumentBuilder builder) {
        if (!builder.hasField(InstrumentFields.LAST_TRADING_DATE)) {
            builder.withField(InstrumentFields.LAST_TRADING_DATE, instrument.getValue(InstrumentFields.LAST_TRADING_DATE));
        }
        if (!builder.hasField(InstrumentFields.DELIVERY_DATE)) {
            builder.withField(InstrumentFields.DELIVERY_DATE, instrument.getValue(InstrumentFields.DELIVERY_DATE));
        }
        if (!builder.hasField(InstrumentFields.MARKET)) {
            builder.withField(InstrumentFields.MARKET, instrument.getValue(InstrumentFields.MARKET));
        }
        if (!builder.hasField(InstrumentFields.LABEL)) {
            builder.withField(InstrumentFields.LABEL, instrument.getValue(InstrumentFields.LABEL));
        }
        if (!builder.hasField(InstrumentFields.TRADABLE)) {
            builder.withField(InstrumentFields.TRADABLE, instrument.getValueOrDefault(InstrumentFields.TRADABLE, "TRUE"));
        }
    }
}
