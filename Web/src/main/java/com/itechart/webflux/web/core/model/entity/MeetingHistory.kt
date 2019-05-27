package com.itechart.webflux.web.core.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "meetingHistory")
class MeetingHistory(@Id override var id: String?, var userId: String?, var meetingId: String?, var usersCount: String?, var date: Date?) : Entity