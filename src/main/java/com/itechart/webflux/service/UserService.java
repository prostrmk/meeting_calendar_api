package com.itechart.webflux.service;

import com.itechart.webflux.model.User;
import com.itechart.webflux.repository.UserRepository;
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
    boolean validate(User user) {
        return true;
    }
}
