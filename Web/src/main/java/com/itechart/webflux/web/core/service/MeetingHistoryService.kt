package com.itechart.webflux.web.core.service

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException
import com.itechart.webflux.web.core.model.entity.MeetingHistory
import com.itechart.webflux.web.core.repository.MeetingHistoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MeetingHistoryService : AbstractService<MeetingHistory> {

    private var repository: MeetingHistoryRepository

    @Autowired
    constructor(meetingHistoryRepository: MeetingHistoryRepository): super(meetingHistoryRepository) {
        this.repository = meetingHistoryRepository
    }

    @Throws(ValidationNotPassedException::class)
    override fun validate(e: MeetingHistory): Boolean {
        return e.meetingId != null && e.userId != null
    }

}