package com.itechart.webflux.web.controller

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException
import com.itechart.webflux.web.core.model.entity.User
import com.itechart.webflux.web.core.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import sun.reflect.generics.reflectiveObjects.NotImplementedException

@CrossOrigin
@RestController
class MainController {

//    @Autowired
//    internal var userHelper: UserHelper? = null

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    lateinit var userService: UserService

    @PostMapping("/registration")
    @Throws(ValidationNotPassedException::class)
    fun registration(user: User): Any {
        throw NotImplementedException()
    }

}