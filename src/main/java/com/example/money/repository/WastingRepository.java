package com.example.money.repository;

import com.example.money.entity.Wasting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WastingRepository extends JpaRepository<Wasting, Long> {

    List<Wasting> findAllByTimePayingIsBetween(LocalDateTime fromDate, LocalDateTime toDate);
}
