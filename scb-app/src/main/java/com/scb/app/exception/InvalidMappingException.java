package com.scb.app.exception;

public class InvalidMappingException extends InstrumentException {

    public InvalidMappingException() {
        super("Invalid mapping key");
    }
}
