package com.itechart.webflux.core.service;

import com.itechart.webflux.core.model.User;
import com.itechart.webflux.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, UserRepository> {

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

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username).block();
    }

}
