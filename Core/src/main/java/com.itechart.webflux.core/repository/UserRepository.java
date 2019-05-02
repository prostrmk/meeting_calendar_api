package com.itechart.webflux.core.repository;

import com.itechart.webflux.core.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

    @Query("SELECT * FROM users WHERE username=$1")
    Mono<User> findUserByUsername(String username);
}
