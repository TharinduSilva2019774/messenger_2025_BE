package com.example.messenger_2025.repository;

import com.example.messenger_2025.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeyRepository extends JpaRepository<Key,Long> {

    Optional<Key> findByDeviceUID(String deviceUID);

}
