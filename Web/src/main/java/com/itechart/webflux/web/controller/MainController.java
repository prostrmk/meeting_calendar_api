package com.itechart.webflux.web.controller;

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.web.core.model.User;
import com.itechart.webflux.web.helpers.UserHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    @GetMapping(value = "/test")
    public String test() {
        return "test";
    }

    @PostMapping(value = "/registration")
    public ResponseEntity registration(User user, UserHelper userHelper) throws ValidationNotPassedException {
        return userHelper.processRegister(user);
    }

    @PostMapping(value = "/auth")
    public ResponseEntity auth(User user, UserHelper userHelper) throws ValidationNotPassedException {
        return userHelper.processAuth(user);
    }




}
