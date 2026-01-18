package com.example.messenger_2025.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.sql.Timestamp;

@Data
@Entity
@EnableAutoConfiguration
@AllArgsConstructor
@NoArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "chat", referencedColumnName = "id")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "encuser", referencedColumnName = "id")
    private User encUser;
}
