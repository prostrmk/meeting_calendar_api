package com.itechart.webflux.web.core.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "meetingHistory")
public class MeetingHistory implements Entity {

    @Id
    private String id;
    private String userId;
    private String meetingId;
    private String usersCount;
    private Date date;

}
