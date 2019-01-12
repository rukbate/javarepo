package com.scb.app.exception;

public class UnknownExchangeException extends InstrumentException {

    public UnknownExchangeException() {
        super("Unknow exchange");
    }
}
