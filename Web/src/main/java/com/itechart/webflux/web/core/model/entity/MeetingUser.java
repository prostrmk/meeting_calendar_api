package com.itechart.webflux.web.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "meetingUser")
public class MeetingUser implements Entity {

    @Id
    private String id;
    private String userId;
    private String meetingId;

}
