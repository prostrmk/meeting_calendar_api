package com.itechart.webflux.handler;

import com.itechart.webflux.model.User;
import com.itechart.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MainController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user")
    public ResponseEntity userPost(User user) {
        Mono save = userService.save(user);
        User block = (User) save.block();
        if (block != null) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }



}
