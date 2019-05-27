package com.itechart.webflux.web.controller

import com.itechart.webflux.web.core.model.dto.UserDto
import com.itechart.webflux.web.core.model.entity.User
import com.itechart.webflux.web.core.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@CrossOrigin
@RequestMapping("/api/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/")
    fun getUsers(@RequestParam name: String): Flux<UserDto> {
        return userService.findUsersByUsernameLike(name)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/profile")
    fun getProfile(): Mono<User> {
        val name = SecurityContextHolder.getContext().authentication.name
        return userService.findUserByUsername(name)
                .doOnSuccess { user -> user.password = null }
    }


}