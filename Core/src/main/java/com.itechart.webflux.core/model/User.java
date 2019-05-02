package com.itechart.webflux.core.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Table("users")
public class User implements Entity{

    @Id
    private Long id;
    private String username;
    private String department;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String room;
    private String firstName;
    private String lastName;
    private String password;



}
