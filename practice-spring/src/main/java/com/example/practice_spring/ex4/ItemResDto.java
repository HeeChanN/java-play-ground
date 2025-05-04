package com.example.practice_spring.ex4;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ItemResDto {
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;

    public ItemResDto(LocalDateTime createdAt, LocalDateTime finishedAt) {
        this.createdAt = createdAt;
        this.finishedAt = finishedAt;
    }
}
