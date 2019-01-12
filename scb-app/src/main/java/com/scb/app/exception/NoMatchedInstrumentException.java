package com.scb.app.exception;

public class NoMatchedInstrumentException extends InstrumentException {

    public NoMatchedInstrumentException() {
        super("No matched instrument");
    }
}
