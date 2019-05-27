package com.itechart.webflux.web.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ResponseUtil {

    companion object {
        private val json = "{\"%s\": \"%s\"}"

        fun respond(response: String, status: HttpStatus): ResponseEntity<String> {
            return ResponseEntity(response, status)
        }

        fun respond(response: String, status: Int): ResponseEntity<String> {
            return respond(response, HttpStatus.valueOf(status))
        }

        fun error(error: String, status: Int): ResponseEntity<String> {
            return respond(String.format(json, "error", error), status)
        }

        fun respond(title: String, text: String, status: Int): ResponseEntity<String> {
            return respond(String.format(json, title, text), status)
        }
    }

}