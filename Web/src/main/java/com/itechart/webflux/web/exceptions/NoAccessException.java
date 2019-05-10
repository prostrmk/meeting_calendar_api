package com.itechart.webflux.web.exceptions;

public class NoAccessException extends RuntimeException {

    public NoAccessException(String s) {
        super(s);
    }
}
