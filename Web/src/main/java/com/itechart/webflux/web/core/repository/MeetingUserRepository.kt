package com.itechart.webflux.web.core.repository

import com.itechart.webflux.web.core.model.entity.MeetingUser
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface MeetingUserRepository : ReactiveMongoRepository<MeetingUser, String> {

    fun findByUserId(userId: String): Flux<MeetingUser>

    fun deleteByUserId(userId: String)

}