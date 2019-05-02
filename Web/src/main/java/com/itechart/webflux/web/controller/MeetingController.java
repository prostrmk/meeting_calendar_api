package com.itechart.webflux.web.controller;

import com.itechart.webflux.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.core.model.Meeting;
import com.itechart.webflux.core.model.MeetingUser;
import com.itechart.webflux.core.model.User;
import com.itechart.webflux.core.service.MeetingService;
import com.itechart.webflux.core.service.MeetingUserService;
import com.itechart.webflux.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

import static com.itechart.webflux.web.util.ResponseUtil.error;
import static com.itechart.webflux.web.util.ResponseUtil.respond;

@RequestMapping(value = "/api")
@RestController
public class MeetingController {


    @Autowired
    private UserService userService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private MeetingUserService meetingUserService;

    private Logger LOGGER = LoggerFactory.getLogger(MeetingController.class);

    @GetMapping(value = "/meeting")
    public Flux<MeetingUser> getMeetings() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User baseUser = userService.findUserByUsername(name);
        return meetingUserService.findByUserId(baseUser.getId());
    }

    @DeleteMapping(value = "/meeting/{id}")
    public ResponseEntity deleteMeeting(@PathVariable Long id) {
        User baseUser = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        try {
            meetingUserService.deleteByUserId(baseUser.getId());
            return respond("status", "removed", 204);
        } catch (Exception e) {
            return error("Something went wrong", 500);
        }
    }

    @PostMapping(value = "/meeting/user")
    public ResponseEntity postMeetingUser(Long meetingId, Long userId) throws ValidationNotPassedException {
        AtomicReference<ResponseEntity> responseEntity = new AtomicReference<>();
        meetingService.findById(meetingId).doOnSuccess(meeting -> {
            if (meeting == null) {
                responseEntity.set(error("No such meeting", 400));
            }
            User user = userService.findById(userId).block();
            if (user == null) {
                responseEntity.set(error("No such user", 400));
            }
            try {
                Mono<MeetingUser> save = meetingUserService.save(new MeetingUser(null, userId, meetingId));
                responseEntity.set(respond("status", "saved", 202));
            } catch (Exception e) {
                LOGGER.error("Something went wrong during saving new meeting user");
                responseEntity.set(error("Something went wrong", 500));
            }
        });
        return responseEntity.get();
    }

    @GetMapping(value = "/meeting/{id}")
    public Mono<Meeting> getMeeting(@PathVariable Long id) {
        return meetingService.findById(id);
    }

}
