package com.gildedrose.inventory.exceptions;


public class GildedRoseException extends RuntimeException {

    public GildedRoseException() {
        super();
    }

    public GildedRoseException(String message) {
        super(message);
    }

    public GildedRoseException(String message, Throwable cause) {
        super(message, cause);
    }

    public GildedRoseException(Throwable cause) {
        super(cause);
    }

    protected GildedRoseException(String message, Throwable cause,
                                  boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
