package com.example.money.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Wasting {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "time_paying")
    private Long timePaying;

    @Column(name = "value")
    private Long value;

    @Column(name = "description")
    private String description;

    public Wasting(Long timePaying, Long value, String description) {
        this.timePaying = timePaying;
        this.value = value;
        this.description = description;
    }
}
