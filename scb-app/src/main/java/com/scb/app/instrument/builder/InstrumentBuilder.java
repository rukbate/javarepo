package com.scb.app.instrument.builder;

import com.scb.app.exception.InstrumentException;
import com.scb.app.exception.MissingFieldException;
import com.scb.app.instrument.model.Instrument;

import java.util.HashMap;
import java.util.Map;

public abstract class InstrumentBuilder {

    private Map<String, String> fields = new HashMap<>(8);

    public Instrument build() throws InstrumentException {
        if(!this.validate()) {
            throw new MissingFieldException();
        }

        return this.buildInstrument();
    }

    abstract boolean validate();

    abstract Instrument buildInstrument();

    public InstrumentBuilder withField(String name, String value) {
        this.fields.put(name, value);
        return this;
    }

    public boolean hasField(String field) {
        return fields.containsKey(field) && fields.get(field) != null;
    }

    Map<String, String> getFields() {
        return this.fields;
    }
}
