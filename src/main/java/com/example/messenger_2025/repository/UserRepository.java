package com.example.messenger_2025.repository;

import com.example.messenger_2025.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> getUserById(long id);

    Optional<User> getUserByClarkId(String clarkId);

}
