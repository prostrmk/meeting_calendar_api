package com.itechart.webflux.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
public class User implements Entity{

    @Id
    private Long id;
    private String username;
    private String department;
    private String room;
    private String firstName;
    private String lastName;
    private String password;



}
