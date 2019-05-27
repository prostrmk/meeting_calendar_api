package com.itechart.webflux.web.controller

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException
import com.itechart.webflux.web.core.model.entity.Message
import com.itechart.webflux.web.core.model.entity.User
import com.itechart.webflux.web.core.service.MessageService
import com.itechart.webflux.web.core.service.UserService
import com.itechart.webflux.web.exceptions.NoAccessException
import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/message")
class MessageController {

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val messageService: MessageService? = null

    private val logger: Logger = LoggerFactory.getLogger(MessageController::class.java)

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    fun getMessages(person: String, size: Int, page: Int): Flux<Message> {
        val user : User;
        val name = SecurityContextHolder.getContext().authentication.name
        val block = userService!!.findUserByUsername(name).block()
        val sort = Sort(Sort.Direction.ASC, "date")
        return messageService!!.findMessages(person, block!!.id, PageRequest.of(page - 1, size, sort))
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Throws(ValidationNotPassedException::class)
    fun saveMessage(message: Message): Mono<Message>? {
        val name = SecurityContextHolder.getContext().authentication.name
        val sender = userService!!.findUserByUsername(name).block()
        message.sender = sender!!.id
        return messageService!!.save(message)
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    @Throws(ValidationNotPassedException::class)
    fun editMessage(message: Message): Mono<Message>? {
        return messageService!!.update(message)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    fun deleteMessage(id: String) {
        messageService!!.findById(id).subscribe { message ->
            if (message != null) {
                userService!!.findUserByUsername(SecurityContextHolder.getContext().authentication.name).subscribe { user ->
                    if (user != null) {
                        if (message.sender == user.id) {
                            try {
                                messageService.delete(message)
                            } catch (e: ValidationNotPassedException) {
                                logger.warn("Error during deleting message: ", e)
                            }

                        } else {
                            throw NoAccessException("You can't delete this message")
                        }
                    }
                }
            }
        }
    }


}