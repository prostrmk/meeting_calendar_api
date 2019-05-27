package com.itechart.webflux.web.core.repository

import com.itechart.webflux.web.core.model.entity.MeetingHistory
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface MeetingHistoryRepository : ReactiveMongoRepository<MeetingHistory, String>