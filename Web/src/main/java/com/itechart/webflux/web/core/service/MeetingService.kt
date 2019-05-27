package com.itechart.webflux.web.core.service

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException
import com.itechart.webflux.web.core.model.entity.Meeting
import com.itechart.webflux.web.core.repository.MeetingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class MeetingService : AbstractService<Meeting> {


    private var repository: MeetingRepository

    @Autowired
    constructor(repository: MeetingRepository) : super(repository) {
        this.repository = repository
    }

    @Throws(ValidationNotPassedException::class)
    override fun validate(e: Meeting): Boolean {
        return e.name != null &&
                e.name!!.length > 2 && e.name!!.length < 50 &&
                e.date != null && e.date!!.time > Date().time
    }


}