package com.example.money.service;

import com.example.money.entity.Money;
import com.example.money.entity.Person;
import com.example.money.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String createPerson(String personName, Long startCapital) {

        final Person person = new Person(personName, new ArrayList<>(), new ArrayList<>());
        LocalDateTime now = LocalDateTime.of(
                LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(),
                1,
                0,
                0
        );
        for (int i = 0; i < 12; i++) {
            final Money money = createMoney(
                    now,
                    startCapital
            );
            person.addMoney(money);
            now = now.plusMonths(1);
        }
        userRepository.save(person);
        return personName;
    }

    private Money createMoney(LocalDateTime time, Long capital) {
        return new Money(time, capital);
    }
}
