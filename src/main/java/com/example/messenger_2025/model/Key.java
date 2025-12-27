package com.example.messenger_2025.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private String key;

    private String deviceUID;

}