package com.scb.app.rule;

import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.InstrumentType;
import com.scb.app.instrument.builder.InstrumentBuilder;
import com.scb.app.instrument.model.Instrument;

import java.util.List;

public class LastTradingDateAndDeliveryDateRule implements Rule {

    @Override
    public void apply(Instrument instrument, List<Instrument> existingInstruments, InstrumentBuilder builder) {
        Instrument targetInstrument = null;

        for(Instrument ins: existingInstruments) {
            if (ins.getType() == InstrumentType.LME) {
                targetInstrument = ins;
                break;
            }
        }

        if (targetInstrument == null) {
            targetInstrument = instrument;
        }

        builder.withField(InstrumentFields.LAST_TRADING_DATE, targetInstrument.getValue(InstrumentFields.LAST_TRADING_DATE));
        builder.withField(InstrumentFields.DELIVERY_DATE, targetInstrument.getValue(InstrumentFields.DELIVERY_DATE));
    }
}
