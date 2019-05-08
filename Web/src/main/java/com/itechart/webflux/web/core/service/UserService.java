package com.itechart.webflux.web.core.service;

import com.itechart.webflux.web.core.model.User;
import com.itechart.webflux.web.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService extends AbstractService<User> {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public boolean validate(User user) {
        return true;
    }

    public Mono<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}
