package com.itechart.webflux.web.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    private static String DEFAULT_JSON = "{\"%s\": \"%s\"}";

    public static ResponseEntity<String> respond(String response, HttpStatus status) {
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<String> respond(String response, int status) {
        return respond(response, HttpStatus.valueOf(status));
    }

    public static ResponseEntity<String> error(String error, int status) {
        return respond(String.format(DEFAULT_JSON, "error", error), status);
    }

    public static ResponseEntity<String> respond(String title, String text, int status) {
        return respond(String.format(DEFAULT_JSON, title, text), status);
    }



}
