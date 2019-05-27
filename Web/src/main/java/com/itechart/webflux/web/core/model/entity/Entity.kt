package com.itechart.webflux.web.core.model.entity

import java.io.Serializable

interface Entity : Serializable {
    var id: String?
}