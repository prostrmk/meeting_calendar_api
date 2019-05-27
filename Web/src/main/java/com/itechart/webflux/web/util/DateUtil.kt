package com.itechart.webflux.web.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {

    fun formatDate(date: Date): String {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        return dateFormat.format(date)
    }

}