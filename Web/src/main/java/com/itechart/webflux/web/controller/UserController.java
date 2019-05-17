package com.itechart.webflux.web.controller;

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.web.core.model.dto.UserDto;
import com.itechart.webflux.web.core.model.entity.User;
import com.itechart.webflux.web.core.service.UserService;
import com.itechart.webflux.web.util.DateUtil;
import com.itechart.webflux.web.util.FileUtil;
import com.itechart.webflux.web.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Date;

import static com.itechart.webflux.web.util.ResponseUtil.respond;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Value("${static.path}")
    private String staticPath;

    @Autowired
    private UserService userService;

    @GetMapping
    public Flux<UserDto> getUsers(@RequestParam String name) {
        return userService.findUsersByUsernameLike(name);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/profile")
    public Mono<User> getProfile() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findUserByUsername(name)
                .doOnSuccess(user -> {
                    user.setPassword(null);
                });
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/profile")
    public ResponseEntity putProfile(User user) {
        if (user == null) {
            return respond("error", "data is invalid", 400);
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.findUserByUsername(name).subscribe(baseUser -> {
            if (baseUser.getEmail() != null && !StringUtil.equals(baseUser.getEmail(), user.getEmail())) {
                userService.findUserByEmail(user.getEmail()).subscribe(byEmail -> {
                    if (byEmail == null) {
                        user.setId(baseUser.getId());
                        try {
                            if (user.getPhoto() != null && user.getPhoto().startsWith("data:")) {
                                String png = FileUtil.saveBase64(user.getPhoto(),
                                        String.format("%s/%s", staticPath, "user/avatar"),
                                        String.format("%s_%s", user.getUsername(), DateUtil.formatDate(new Date())),
                                        "png");
                                if (png != null) {
                                    user.setPhoto(png);
                                }
                            }
                            user.setPassword(baseUser.getPassword());
                            userService.update(user);
                        } catch (ValidationNotPassedException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        return ResponseEntity.ok(null);
    }

}
