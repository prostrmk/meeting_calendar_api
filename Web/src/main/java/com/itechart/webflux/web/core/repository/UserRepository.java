package com.itechart.webflux.web.core.repository;

import com.itechart.webflux.web.core.model.dto.UserDto;
import com.itechart.webflux.web.core.model.entity.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findUserByUsername(String username);

    Flux<UserDto> findUsersByUsernameLike(String usernameLike);

//    @Query("{$project: {name: {$concat: [\"$firstName\", \" \", \"$lastName\"]}}}, {$match: {name: new RegExp(\":name\", 'i')}})")
    @Query("{firstName: ?0}")
    Flux<User> findUsersByFirstAndLastNames(@Param("name") String name);
}
