package com.example.tournamentmatches.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsernameOrEmail(String username, String email);


    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserById(Long id);
}
