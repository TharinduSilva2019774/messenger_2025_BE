package com.example.messenger_2025.repository;

import com.example.messenger_2025.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findTop20ByOrderByIdDesc();

}
