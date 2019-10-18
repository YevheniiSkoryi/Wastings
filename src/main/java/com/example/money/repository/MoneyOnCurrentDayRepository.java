package com.example.money.repository;

import com.example.money.entity.MoneyOnCurrentDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoneyOnCurrentDayRepository extends JpaRepository<MoneyOnCurrentDay, Long> {

    Optional<MoneyOnCurrentDay> findByCurrentDate(Long currentDay);

}
