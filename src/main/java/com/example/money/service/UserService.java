package com.example.money.service;

import com.example.money.config.ErrorException;
import com.example.money.config.ErrorType;
import com.example.money.entity.Money;
import com.example.money.entity.Person;
import com.example.money.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String createPerson(String personName, Long startCapital) {

        Optional<Person> uniquePerson = userRepository.findById(personName);
        if (uniquePerson.isPresent()) {
            throw new ErrorException(ErrorType.PERON_ALREADY_EXIST, "Person " + personName + " already exist");
        }

        final Person person = new Person(personName, new ArrayList<>(), new ArrayList<>());
        LocalDateTime now = LocalDateTime.of(
                LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(),
                1,
                0,
                0
        );
        person.addMoney(new Money(now, startCapital));
        userRepository.save(person);
        return personName;
    }
}
