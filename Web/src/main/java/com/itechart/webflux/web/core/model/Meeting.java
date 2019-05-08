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
@Document(collection = "meeting")
public class Meeting implements Entity {

    @Id
    private String id;
    private String name;
    private Date date;

}
