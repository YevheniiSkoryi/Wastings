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
public class MoneyOnCurrentDay {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "date_of_month")
    private Long currentDate;

    @Column(name = "value")
    private Long value;

    public MoneyOnCurrentDay(Long currentDate, Long value) {
        this.currentDate = currentDate;
        this.value = value;
    }
}
