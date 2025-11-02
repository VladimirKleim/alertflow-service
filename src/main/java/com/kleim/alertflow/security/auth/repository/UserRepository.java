package com.kleim.alertflow.security.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByLogin(
            @Param("login") String login
    );

    Optional<UserEntity> findByLogin(
            @Param("login") String login
    );
}
