package com.itechart.webflux.web.core.service

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException
import com.itechart.webflux.web.core.model.entity.Message
import com.itechart.webflux.web.core.repository.MessageRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class MessageService : AbstractService<Message> {

    private val repository: MessageRepository

    @Autowired
    constructor(repository: MessageRepository): super(repository) {
        this.repository = repository
    }

    @Throws(ValidationNotPassedException::class)
    override fun save(e: Message): Mono<Message>? {
        e.date = Date()
        e.exists = true
        return super.save(e)
    }

    fun findMessages(receiver: String?, sender: String?, pageable: Pageable): Flux<Message> {
        return repository.findByReceiverAndSenderOrSenderAndReceiver(receiver, sender, sender, receiver, pageable)
    }

    override fun validate(e: Message): Boolean {
        return e.receiver != null && e.sender != null && e.text != null
    }

    override fun delete(e: Message?): Mono<Message>? {
        e!!.exists = false
        return update(e)
    }


}