package com.scb.app.service;

import com.scb.app.exception.InstrumentException;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.rule.Rule;

public interface InstrumentService {

    void addRule(Rule rule);

    void addInstrument(Instrument instrument);

    Instrument publish(String exchange, String code) throws InstrumentException;
}
