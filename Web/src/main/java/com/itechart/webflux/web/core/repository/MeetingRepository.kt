package com.itechart.webflux.web.core.repository

import com.itechart.webflux.web.core.model.entity.Meeting
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface MeetingRepository : ReactiveMongoRepository<Meeting, String>