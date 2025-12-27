package com.example.messenger_2025.repository;

import com.example.messenger_2025.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<Key,Long> {
}
