package com.scb.app.instrument;

public enum InstrumentType {
    STANDARD(false),
    LME(true),
    PRIME(true);

    private boolean exchange;

    InstrumentType(boolean exchange) {
        this.exchange = exchange;
    }

    public static InstrumentType fromName(String name) {
        for(InstrumentType type: InstrumentType.values()) {
            if(type.name().equals(name) && type.exchange) {
                return type;
            }
        }

        return null;
    }

    public boolean isExchange() {
        return exchange;
    }
}
