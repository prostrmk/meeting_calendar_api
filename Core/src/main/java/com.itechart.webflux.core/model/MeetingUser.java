package com.itechart.webflux.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetingUser implements Entity {

    @Id
    private Long id;
    private Long userId;
    private Long meetingId;

}
