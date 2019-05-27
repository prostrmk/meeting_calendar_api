package com.itechart.webflux.web.core.repository

import com.itechart.webflux.web.core.model.entity.Message
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface MessageRepository : ReactiveMongoRepository<Message, String> {
    fun findByReceiverAndSenderOrSenderAndReceiver(receiver: String?, sender: String?, sender1: String?, receiver2: String?, pageable: Pageable): Flux<Message>
}