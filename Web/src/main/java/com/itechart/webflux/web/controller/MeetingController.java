package com.itechart.webflux.web.controller;

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.web.core.model.Meeting;
import com.itechart.webflux.web.core.model.MeetingHistory;
import com.itechart.webflux.web.core.model.MeetingUser;
import com.itechart.webflux.web.core.model.User;
import com.itechart.webflux.web.core.service.MeetingHistoryService;
import com.itechart.webflux.web.core.service.MeetingService;
import com.itechart.webflux.web.core.service.MeetingUserService;
import com.itechart.webflux.web.core.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@CrossOrigin
@RequestMapping(value = "/api/meeting")
@RestController
public class MeetingController {


    @Autowired
    private UserService userService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private MeetingUserService meetingUserService;

    @Autowired
    private MeetingHistoryService meetingHistoryService;

    private Logger LOGGER = LoggerFactory.getLogger(MeetingController.class);

    @GetMapping
    public Flux<Meeting> getMeetings() {
        return meetingService.findAll();
    }

    @PostMapping
    public Mono<Meeting> postMeeting(Meeting meeting, ArrayList<String> idsStr) throws ValidationNotPassedException {
        return meetingService.save(meeting).doOnSuccess(saved -> {
            String meetingId = saved.getId();
            idsStr.forEach(id -> {
                try {
                    meetingUserService.save(new MeetingUser(null, id, meetingId));
                } catch (ValidationNotPassedException e) {
                    log.error("Validation error", e);
                }
            });
        });
    }

    @DeleteMapping(value = "/{id}")
    public Mono<User> deleteMeeting(@PathVariable String id) {
        return userService.findUserByUsername("asd");
    }

    @GetMapping(value = "/user")
    public Flux<User> getUsers() {
        return userService.findAll();
    }

    @PostMapping(value = "/user")
    public ResponseEntity postMeetingUser(String meetingId, String userId) throws ValidationNotPassedException {
        return null;
    }

    @GetMapping(value = "/{id}")
    public Mono<Meeting> getMeeting(@PathVariable String id) {
        return meetingService.findById(id);
    }

    @GetMapping(value = "/history")
    public Flux<MeetingHistory> getHistory() {
        return meetingHistoryService.findAll();
    }

}
