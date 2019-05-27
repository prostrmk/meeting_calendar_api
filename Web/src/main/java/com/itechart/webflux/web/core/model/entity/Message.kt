package com.itechart.webflux.web.core.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "message")
class Message(@Id override var id: String?, var sender: String?, var receiver: String?, var text: String?, var date: Date, var exists: Boolean) : Entity
