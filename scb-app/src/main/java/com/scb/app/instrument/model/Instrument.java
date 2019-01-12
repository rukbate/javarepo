package com.scb.app.instrument.model;

import com.scb.app.instrument.InstrumentFields;
import com.scb.app.instrument.InstrumentType;

import java.util.Map;

public abstract class Instrument {
    private final InstrumentType type;
    private final Map<String, String> fields;
    private String mappingKey;

    public Instrument(InstrumentType type, Map<String, String> fields) {
        this.type = type;
        this.fields = fields;
        mappingKey = InstrumentFields.CODE;
    }

    public boolean match(String code) {
        return this.hasField(mappingKey) && this.getValue(mappingKey).equals(code);
    }

    public String getValue(String field) {
        return this.fields.get(field);
    }

    public String getValueOrDefault(String field, String defaultValue) {
        return this.hasField(field) ? this.getValue(field) : defaultValue;
    }

    private boolean hasField(String field) {
        return fields.containsKey(field) && fields.get(field) != null;
    }

    public void setMappingKey(String key) {
        this.mappingKey = key;
    }

    public InstrumentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                InstrumentFields.LAST_TRADING_DATE + "=" + this.getValue(InstrumentFields.LAST_TRADING_DATE) +
                ", " + InstrumentFields.DELIVERY_DATE + "=" + this.getValue(InstrumentFields.DELIVERY_DATE) +
                ", " + InstrumentFields.MARKET + "='" + this.getValue(InstrumentFields.MARKET) + '\'' +
                ", " + InstrumentFields.LABEL + "='" + this.getValue(InstrumentFields.LABEL) + '\'' +
                ", " + InstrumentFields.TRADABLE + "='" + this.getValue(InstrumentFields.TRADABLE) + '\'' +
                '}';
    }
}
