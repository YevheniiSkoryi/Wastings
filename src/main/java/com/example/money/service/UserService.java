package com.example.money.service;

import com.example.money.config.ErrorException;
import com.example.money.config.ErrorType;
import com.example.money.dto.application.PersonData;
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

    public String createPerson(PersonData data) {

        Optional<Person> uniquePerson = userRepository.findById(data.getPersonName());

        if (uniquePerson.isPresent()) {
            throw new ErrorException(ErrorType.PERSON_ALREADY_EXIST, "Person " + data.getPersonName() + " already exist");
        }
        final Person person = new Person(data.getPersonName(), new ArrayList<>(), new ArrayList<>());
        LocalDateTime now = LocalDateTime.of(
                LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(),
                1,
                0,
                0
        );
        person.addMoney(new Money(now, data.getStartCapital()));
        userRepository.save(person);
        return data.getPersonName();
    }

    public String getPerson(String userName) {
        Person person = userRepository.findById(userName)
                .orElseThrow(() -> new ErrorException(ErrorType.PERSON_NOT_FOUND, "Person " + userName + " not found"));

        return person.getId();

    }
}
