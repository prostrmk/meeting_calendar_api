package com.itechart.webflux.web.helpers;

import com.itechart.webflux.web.config.jwt.JwtGen;
import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.web.core.model.entity.User;
import com.itechart.webflux.web.core.model.entity.UserRole;
import com.itechart.webflux.web.core.service.UserService;
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
    private JwtGen jwtGen;

    public ResponseEntity processRegister(User user) throws ValidationNotPassedException {
        if (user == null) {
            return error("Bad request", 400);
        }
        User byName = userService.findUserByUsername(user.getUsername()).block();
        if (byName != null) {
            return error("User with this username already exists", 400);
        }
        if (userService.validate(user) && user.getPassword() != null && user.getPassword().length() >= 6) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(UserRole.USUAL);
            user.setActive(true);
            Mono<User> save = userService.save(user);
            save.subscribe(System.out::println);
            return respond("ok", 200);
        }
        return error("Bad data", 400);
    }

    public ResponseEntity processAuth(User user) {
        if (user == null) {
            return error("bad request", 400);
        }
        User baseUser = userService.findUserByUsername(user.getUsername()).block();
        if (baseUser == null) {
            return error("no such user", 400);
        }
        boolean matches = passwordEncoder.matches(user.getPassword(), baseUser.getPassword());
        if (matches) {
            String token = jwtGen.generate(user);
            String resp = String.format("{\"%s\": \"%s\", \"%s\": \"%s\"}", "token", token, "id", user.getId());
            return respond(resp, 200);
        } else {
            return error("Invalid password", 400);
        }
    }


}
