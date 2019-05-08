package com.itechart.webflux.web.core.service;

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.web.core.model.MeetingHistory;
import com.itechart.webflux.web.core.repository.MeetingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingHistoryService extends AbstractService<MeetingHistory> {

    private MeetingHistoryRepository repository;

    @Autowired
    public MeetingHistoryService(MeetingHistoryRepository meetingHistoryRepository) {
        super(meetingHistoryRepository);
        this.repository = meetingHistoryRepository;
    }

    @Override
    public boolean validate(MeetingHistory meetingHistory) throws ValidationNotPassedException {
        return meetingHistory != null && meetingHistory.getMeetingId() != null && meetingHistory.getUserId() != null;
    }
}
