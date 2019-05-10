package com.itechart.webflux.web.controller;

import com.itechart.webflux.web.core.model.dto.UserDto;
import com.itechart.webflux.web.core.model.entity.User;
import com.itechart.webflux.web.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Flux<UserDto> getUsers(@RequestParam String name) {
        return userService.findUsersByUsernameLike(name);
    }

}
