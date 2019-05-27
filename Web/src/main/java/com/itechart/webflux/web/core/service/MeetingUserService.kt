package com.itechart.webflux.web.core.service

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException
import com.itechart.webflux.web.core.model.entity.MeetingUser
import com.itechart.webflux.web.core.repository.MeetingUserRepository
import org.springframework.beans.factory.annotation.Autowired
import reactor.core.publisher.Flux

class MeetingUserService : AbstractService<MeetingUser> {

    private val repository: MeetingUserRepository

    @Autowired
    constructor(repository: MeetingUserRepository): super(repository) {
        this.repository = repository
    }

    @Throws(ValidationNotPassedException::class)
    override fun validate(e: MeetingUser): Boolean {
        return false
    }

    fun findByUserId(userId: String): Flux<MeetingUser> {
        return repository.findByUserId(userId)
    }

    fun deleteByUserId(userId: String) {
        repository.deleteByUserId(userId)
    }

}