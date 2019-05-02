package com.itechart.webflux.core.repository;

import com.itechart.webflux.core.model.Meeting;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends ReactiveCrudRepository<Meeting, Long> {

}
