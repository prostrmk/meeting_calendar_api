package com.itechart.webflux.web.core.model.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "meetingUser")
class MeetingUser(@Id override var id: String?, var userId: String?, var meetingId: String?) : Entity