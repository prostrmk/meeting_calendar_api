package com.itechart.webflux.web.core.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "meeting")
class Meeting(@Id override var id: String?, var name: String?, var date: Date?) : Entity