package com.example.money.repository;

import com.example.money.entity.Money;
import com.example.money.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MoneyRepository extends JpaRepository<Money, Long> {

    Optional<Money> findByCurrentDateAndPerson(LocalDateTime currentDay, Person person);
    Optional<Money> findFirstByPersonOrderByIdDesc(Person person);

}
