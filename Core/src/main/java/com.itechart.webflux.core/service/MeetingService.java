package com.itechart.webflux.core.service;

import com.itechart.webflux.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.core.model.Meeting;
import com.itechart.webflux.core.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MeetingService extends AbstractService<Meeting, MeetingRepository> {

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
                meeting.getDateTime() != null && meeting.getDateTime().getTime() > new Date().getTime();
    }



}
