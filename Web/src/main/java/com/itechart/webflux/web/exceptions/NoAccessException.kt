package com.itechart.webflux.web.exceptions

import java.lang.RuntimeException

class NoAccessException(message: String?) : RuntimeException(message)