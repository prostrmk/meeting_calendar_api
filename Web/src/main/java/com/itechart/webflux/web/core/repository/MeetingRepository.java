package com.itechart.webflux.web.core.repository;

import com.itechart.webflux.web.core.model.Meeting;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MeetingRepository extends ReactiveMongoRepository<Meeting, String> {

}