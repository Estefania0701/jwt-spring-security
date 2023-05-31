package com.eas.tutorial.jwtspringsecurity.repository;

import com.eas.tutorial.jwtspringsecurity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findByCountry(String country);
}
