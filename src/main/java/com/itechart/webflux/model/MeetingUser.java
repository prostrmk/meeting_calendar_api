package com.itechart.webflux.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class MeetingUser {

    @Id
    private Long id;
    private Long userId;
    private Long meetingId;

}
