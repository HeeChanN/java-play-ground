package com.example.practice_spring.ex4;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

    public Item(LocalDateTime createdAt, LocalDateTime finishedAt) {
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
    }
}
