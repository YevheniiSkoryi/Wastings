package com.example.money.entity;

import com.example.money.dto.WastingDTO;
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

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "person"
    )
    private List<Money> monies;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "person"
    )
    private List<Wasting> wastings;




    public void addWasting(final WastingDTO money) {
        wastings.add(new Wasting(
                money.getTimePaying(),
                this,
                money.getValue(),
                money.getDescription()
        ));
    }

    public void addMoney(final Money money) {
        monies.add(new Money(
                money.getCurrentDate(),
                money.getValue(),
                this

        ));
    }
}
