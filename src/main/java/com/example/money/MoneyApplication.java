package com.example.money;

import com.example.money.entity.Person;
import com.example.money.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        final Person person = new Person("yevhenii", Collections.emptyList(),Collections.emptyList());
        userRepository.save(person);
    }
}
