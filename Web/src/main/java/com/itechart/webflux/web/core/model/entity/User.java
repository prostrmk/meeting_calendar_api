package com.itechart.webflux.web.core.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User implements Entity {

    @Id
    private String id;
    private String username;
    private String department;
    private UserRole role;
    private String room;
    private String firstName;
    private String lastName;
    private String password;
    private boolean active;

    public User(String username, UserRole role) {
        this.username = username;
        this.role = role;
    }
}
