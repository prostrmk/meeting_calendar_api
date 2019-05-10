package com.itechart.webflux.web.core.service;

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.web.core.model.entity.MeetingUser;
import com.itechart.webflux.web.core.repository.MeetingUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MeetingUserService extends AbstractService<MeetingUser> {

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

    public Flux<MeetingUser> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    public void deleteByUserId(String userId) {
        repository.deleteByUserId(userId);
    }
}
