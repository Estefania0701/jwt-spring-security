package com.eas.tutorial.jwtspringsecurity.repository;

import com.eas.tutorial.jwtspringsecurity.model.Authority;
import com.eas.tutorial.jwtspringsecurity.util.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository <Authority, Long> {

    Optional<Authority> findByName(AuthorityName name);
}
