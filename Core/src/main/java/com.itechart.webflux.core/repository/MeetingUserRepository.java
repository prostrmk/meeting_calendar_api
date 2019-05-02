package com.itechart.webflux.core.repository;

import com.itechart.webflux.core.model.MeetingUser;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MeetingUserRepository extends ReactiveCrudRepository<MeetingUser, Long> {

    @Query("SELECT * FROM meeting_user WHERE user_id=$1")
    Flux<MeetingUser> findByUserId(Long userId);

    @Query("DELETE FROM meeting_user WHERE user_id=$1")
    void deleteByUserId(Long userId);
}
