package com.scb.app.rule;

import com.scb.app.instrument.builder.InstrumentBuilder;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.InstrumentFields;

import java.util.List;

public class MarketRule implements Rule {

    @Override
    public void apply(String exchange, List<Instrument> components, InstrumentBuilder builder) {
        Instrument targetInstrument = null;

        for(Instrument instrument: components) {
            if (instrument.getType().name().equals(exchange)) {
                targetInstrument = instrument;
            }
        }

        if(targetInstrument != null) {
            builder.withMarket(this.extractMarket(targetInstrument.getValue(InstrumentFields.MARKET)));
        }
    }

    String extractMarket(String input) {
        if(input == null) {
            return null;
        }

        String[] parts = input.split("_");
        return parts[parts.length - 1];
    }
}
