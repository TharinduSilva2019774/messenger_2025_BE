package com.example.messenger_2025.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.sql.Timestamp;

@Data
@Entity
@EnableAutoConfiguration
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String messageBody;

    @CreationTimestamp
    private Timestamp time;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

}
