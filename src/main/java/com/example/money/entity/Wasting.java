package com.example.money.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Wasting {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "time_paying")
    private LocalDateTime timePaying;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Person person;

    @Column(name = "value")
    private Long value;

    @Column(name = "description")
    private String description;

    public Wasting(LocalDateTime timePaying, Person person, Long value, String description) {
        this.timePaying = timePaying;
        this.person = person;
        this.value = value;
        this.description = description;
    }
}
