package com.itechart.webflux.web.util

class StringUtil {

    fun equals(first: String?, second: String?): Boolean {
        return if (first != null) {
            first == second
        } else second == null
    }

}