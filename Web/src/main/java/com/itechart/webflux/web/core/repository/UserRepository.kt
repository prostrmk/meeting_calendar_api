package com.itechart.webflux.web.core.repository

import com.itechart.webflux.web.core.model.dto.UserDto
import com.itechart.webflux.web.core.model.entity.User
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveMongoRepository<User, String> {

    fun findUserByUsername(username: String): Mono<User>

    fun findUsersByUsernameLike(usernameLike: String): Flux<UserDto>

    @Query("{firstName: ?0}")
    fun findUsersByFirstAndLastNames(@Param("name") name: String): Flux<User>

    fun findUserByEmail(email: String): Mono<User>

}