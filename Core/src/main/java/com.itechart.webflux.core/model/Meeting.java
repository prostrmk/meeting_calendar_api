package com.itechart.webflux.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table("meeting")
public class Meeting implements Entity {

    @Id
    private Long id;
    private String name;
    private Date dateTime;

}
