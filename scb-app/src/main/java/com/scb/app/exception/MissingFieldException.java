package com.scb.app.exception;

public class MissingFieldException extends InstrumentException {

    public MissingFieldException() {
        super("Missing required field");
    }
}
