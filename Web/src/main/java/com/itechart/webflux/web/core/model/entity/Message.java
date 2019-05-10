package com.itechart.webflux.web.core.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
public class Message implements Entity {

    @Id
    private String id;
    private String sender;
    private String receiver;
    private String text;
    private Date date;
    private boolean exists;

}
