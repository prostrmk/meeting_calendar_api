package com.itechart.webflux.core.exceptions;

public class ValidationNotPassedException extends Exception {

    public ValidationNotPassedException() {
    }

    public ValidationNotPassedException(String s) {
        super(s);
    }
}
