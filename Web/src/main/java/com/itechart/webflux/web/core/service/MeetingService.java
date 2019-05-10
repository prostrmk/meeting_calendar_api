package com.itechart.webflux.web.core.service;

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.web.core.model.entity.Meeting;
import com.itechart.webflux.web.core.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class MeetingService extends AbstractService<Meeting> {

    private MeetingRepository repository;

    @Autowired
    public MeetingService(MeetingRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public boolean validate(Meeting meeting) throws ValidationNotPassedException {
        return meeting != null && meeting.getName() != null &&
                meeting.getName().length() > 2 && meeting.getName().length() < 50 &&
                meeting.getDate() != null && meeting.getDate().getTime() > new Date().getTime();
    }



}
