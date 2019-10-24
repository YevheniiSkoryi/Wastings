package com.example.money;

import com.example.money.entity.Money;
import com.example.money.entity.Person;
import com.example.money.repository.MoneyRepository;
import com.example.money.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@AllArgsConstructor
public class MoneyApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(MoneyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        final Person person = new Person("YEVHENII SKORYI", new ArrayList<>(),new ArrayList<>());

        final Money money1 = new Money(
                LocalDateTime.of(2019,11,1,0,0),
                (long)1000
        );
        final Money money2 = new Money(
                LocalDateTime.of(2019,12,1,0,0),
                (long)1000
        );
        final Money money3 = new Money(
                LocalDateTime.of(2020,1,1,0,0),
                (long)1000
        );
        final Money money4 = new Money(
                LocalDateTime.of(2020,2,1,0,0),
                (long)1000
        );
        final Money money5 = new Money(
                LocalDateTime.of(2020,3,1,0,0),
                (long)1000
        );
        final Money money6 = new Money(
                LocalDateTime.of(2020,4,1,0,0),
                (long)1000
        );
        final Money money7 = new Money(
                LocalDateTime.of(2020,5,1,0,0),
                (long)1000
        );
        final Money money8 = new Money(
                LocalDateTime.of(2020,6,1,0,0),
                (long)1000
        );
        final Money money9 = new Money(
                LocalDateTime.of(2020,7,1,0,0),
                (long)1000
        );
        final Money money10 = new Money(
                LocalDateTime.of(2020,8,1,0,0),
                (long)1000
        );
        final  Money money11 = new Money(
                LocalDateTime.of(2020,9,1,0,0),
                (long)1000
        );
        final Money money12 = new Money(
                LocalDateTime.of(2020,10,1,0,0),
                (long)1000
        );

        person.addMoney(money1);
        person.addMoney(money2);
        person.addMoney(money3);
        person.addMoney(money4);
        person.addMoney(money5);
        person.addMoney(money6);
        person.addMoney(money7);
        person.addMoney(money8);
        person.addMoney(money9);
        person.addMoney(money10);
        person.addMoney(money11);
        person.addMoney(money12);
        userRepository.save(person);
    }
}
