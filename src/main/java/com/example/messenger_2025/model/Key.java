package com.example.messenger_2025.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@Data
@Entity
@EnableAutoConfiguration
@AllArgsConstructor
@NoArgsConstructor
public class Key {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 1024)
    private String key;

    @Column(unique = true, nullable = false)
    private String deviceUID;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id")
    private User user;

}