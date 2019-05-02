package com.itechart.webflux.core.service;

import com.itechart.webflux.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.core.model.MeetingUser;
import com.itechart.webflux.core.repository.MeetingUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MeetingUserService extends AbstractService<MeetingUser, MeetingUserRepository> {

    private MeetingUserRepository repository;

    @Autowired
    public MeetingUserService(MeetingUserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public boolean validate(MeetingUser meetingUser) throws ValidationNotPassedException {
        return false;
    }

    public Flux<MeetingUser> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public void deleteByUserId(Long userId) {
        repository.deleteByUserId(userId);
    }
}
