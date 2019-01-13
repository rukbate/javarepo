package com.scb.app.rule;

import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.builder.InstrumentBuilder;
import com.scb.app.instrument.model.Instrument;

import java.util.List;

public class MarketRule implements Rule {

    @Override
    public void apply(Instrument instrument, List<Instrument> existingInstruments, InstrumentBuilder builder) {
        builder.withField(InstrumentFields.MARKET, this.extractMarket(instrument.getValue(InstrumentFields.MARKET)));
    }

    String extractMarket(String input) {
        if(input == null) {
            return null;
        }

        String[] parts = input.split("_");
        return parts[parts.length - 1];
    }
}
