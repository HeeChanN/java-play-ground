package com.example.practice_spring.ex4;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ItemCreateReqDto {
    private LocalDateTime finishedAt;
}
