package com.example.money.entity;

import com.example.money.dto.MoneyDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person {

    @Id
    private String id;

    @OneToMany
    private List<MoneyOnCurrentDay> moneyOnCurrentDays;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "person"
    )
    private List<Wasting> wastings;

    public void addWasting(final MoneyDTO money) {
        wastings.add(new Wasting(
                money.getTimePaying(),
                this,
                money.getValue(),
                money.getDescription()
        ));
    }
}
