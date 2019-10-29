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


        person.addMoney(money1);
        userRepository.save(person);
    }
}
