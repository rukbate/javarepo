package com.scb.app.rule;

import com.scb.app.instrument.builder.InstrumentBuilder;
import com.scb.app.instrument.model.Instrument;

import java.util.List;

public class TradableRule implements Rule {

    @Override
    public void apply(String exchange, List<Instrument> components, InstrumentBuilder builder) {
        Instrument targetInstrument = null;

        for(Instrument instrument: components) {
            if (instrument.getType().name().equals(exchange)) {
                targetInstrument = instrument;
            }
        }

        if(targetInstrument != null) {
            builder.withTradable(targetInstrument.isTradable());
        }
    }
}
