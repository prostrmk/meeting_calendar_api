package com.itechart.webflux.web.core.service

import com.itechart.webflux.web.core.model.dto.UserDto
import com.itechart.webflux.web.core.model.entity.User
import com.itechart.webflux.web.core.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService : AbstractService<User>{

    private val userRepository: UserRepository

    @Autowired
    constructor(userRepository: UserRepository) : super(userRepository) {
        this.userRepository = userRepository
    }

    override fun validate(e: User): Boolean {
        return true
    }

    fun findUserByUsername(username: String): Mono<User> {
        return userRepository.findUserByUsername(username)
    }

    fun findUsersByUsernameLike(username: String): Flux<UserDto> {
        return userRepository.findUsersByUsernameLike(String.format("*%s*", username))
    }

    fun findUsersByFirstNameAndLastName(name: String): Flux<User> {
        return userRepository.findUsersByFirstAndLastNames(name)
    }

    fun findUserByEmail(email: String): Mono<User> {
        return userRepository.findUserByEmail(email)
    }

}