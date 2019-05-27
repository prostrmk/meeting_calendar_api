package com.itechart.webflux.web.controller

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException
import com.itechart.webflux.web.core.model.entity.Meeting
import com.itechart.webflux.web.core.model.entity.MeetingHistory
import com.itechart.webflux.web.core.model.entity.MeetingUser
import com.itechart.webflux.web.core.model.entity.User
import com.itechart.webflux.web.core.service.MeetingHistoryService
import com.itechart.webflux.web.core.service.MeetingService
import com.itechart.webflux.web.core.service.MeetingUserService
import com.itechart.webflux.web.core.service.UserService
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.ArrayList

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/meeting")
class MeetingController {

    @Autowired
    private val userService: UserService? = null

    @Autowired
    private val meetingService: MeetingService? = null

    @Autowired
    private val meetingUserService: MeetingUserService? = null

    @Autowired
    private val meetingHistoryService: MeetingHistoryService? = null

    private val logger = LoggerFactory.getLogger(MeetingController::class.java)

    @PostMapping
    @Throws(ValidationNotPassedException::class)
    fun postMeeting(meeting: Meeting, idsStr: ArrayList<String>): Mono<Meeting> {
        return meetingService!!.save(meeting)!!.doOnSuccess { saved ->
            val meetingId = saved.id
            idsStr.forEach { id ->
                try {
                    meetingUserService!!.save(MeetingUser(null, id, meetingId))
                } catch (e: ValidationNotPassedException) {
                    logger.error("Validation error", e)
                }
            }
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMeeting(@PathVariable id: String): Mono<User> {
        return userService!!.findUserByUsername("asd")
    }

    @GetMapping("/user")
    fun getUsers(): Flux<User> {
        return userService!!.findAll()
    }

    @PostMapping("/user")
    @Throws(ValidationNotPassedException::class)
    fun postMeetingUser(meetingId: String, userId: String): ResponseEntity<*>? {
        return null
    }

    @GetMapping("/{id}")
    fun getMeeting(@PathVariable id: String): Mono<Meeting> {
        return meetingService!!.findById(id)
    }

    @GetMapping("/history")
    fun getHistory(): Flux<MeetingHistory> {
        return meetingHistoryService!!.findAll()
    }


}