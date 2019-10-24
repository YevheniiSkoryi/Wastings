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
public class Money {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date_of_month")
    private LocalDateTime currentDate;

    @Column(name = "value")
    private Long value;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Person person;

    public Money(LocalDateTime currentDate, Long value) {
        this.currentDate = currentDate;
        this.value = value;
    }

    public Money(LocalDateTime currentDate, Long value, Person person) {
        this.currentDate = currentDate;
        this.value = value;
        this.person = person;
    }
}
