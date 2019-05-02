package com.itechart.webflux.core.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Meeting {

    @Id
    private Long id;
    private String name;
    private Date dateTime;

}
