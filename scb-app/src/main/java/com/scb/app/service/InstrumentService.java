package com.scb.app.service;

import com.scb.app.exception.InstrumentException;
import com.scb.app.instrument.model.Instrument;
import com.scb.app.rule.Rule;

public interface InstrumentService {

    void addRule(Rule rule);

    Instrument publish(Instrument instrument) throws InstrumentException;
}
