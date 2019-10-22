package com.example.money.repository;

import com.example.money.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Person, String> {
}
