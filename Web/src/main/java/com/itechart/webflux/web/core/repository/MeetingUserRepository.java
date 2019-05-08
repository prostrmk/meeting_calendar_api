package com.itechart.webflux.web.core.repository;

import com.itechart.webflux.web.core.model.MeetingUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MeetingUserRepository extends ReactiveMongoRepository<MeetingUser, String> {

    Flux<MeetingUser> findByUserId(String userId);

    void deleteByUserId(String userId);
}
