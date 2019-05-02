package com.itechart.webflux.web.helpers;

import com.itechart.webflux.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.core.model.User;
import com.itechart.webflux.core.service.UserService;
import com.itechart.webflux.web.config.JwtTokenProvider;
import com.itechart.webflux.web.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static com.itechart.webflux.web.util.ResponseUtil.error;
import static com.itechart.webflux.web.util.ResponseUtil.respond;

@Component
public class UserHelper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public ResponseEntity processRegister(User user) throws ValidationNotPassedException {
        if (user == null) {
            return error("Invalid data", 400);
        }
        User username = userService.findUserByUsername(user.getUsername());
        if (username != null) {
            error("User with this username already exists", 400);
        }
        if (!userService.validate(user)) {
            return error("bad request", 400);
        }
        Mono<User> save = userService.save(user);
        User block = save.block();
        if (block != null) {
            return respond("status", "created", 200);
        }
        return error("server error", 500);
    }

    public ResponseEntity processAuth(User user) {
        if (user == null) {
            return error("bad request", 400);
        }
        User baseUser = userService.findUserByUsername(user.getUsername());
        if (baseUser == null) {
            return error("no such user", 400);
        }
        boolean matches = passwordEncoder.matches(user.getPassword(), baseUser.getPassword());
        if (matches) {
            String token = jwtTokenProvider.createToken(user.getUsername(), Collections.singletonList(user.getRole()));
            return respond("token", token, 200);
        } else {
            return error("Invalid password", 400);
        }

    }


}
