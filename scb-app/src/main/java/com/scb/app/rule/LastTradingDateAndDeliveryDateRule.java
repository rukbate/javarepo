package com.scb.app.rule;

import com.scb.app.instrument.builder.InstrumentBuilder;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.instrument.InstrumentType;

import java.util.List;

public class LastTradingDateAndDeliveryDateRule implements Rule {

    @Override
    public void apply(String exchange, List<Instrument> components, InstrumentBuilder builder) {
        Instrument lmeInstrument = null;
        Instrument targetInstrument = null;

        for(Instrument instrument: components) {
            if (instrument.getType() == InstrumentType.LME) {
                lmeInstrument = instrument;
            }

            if (instrument.getType().name().equals(exchange)) {
                targetInstrument = instrument;
            }
        }

        if (lmeInstrument != null) {
            targetInstrument = lmeInstrument;
        }

        if(targetInstrument != null) {
            builder.withLastTradingDate(targetInstrument.getLastTradingDate());
            builder.withDeliveryDate(targetInstrument.getDeliveryDate());
        }
    }
}
