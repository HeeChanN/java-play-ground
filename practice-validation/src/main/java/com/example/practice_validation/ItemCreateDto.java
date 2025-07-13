package com.example.practice_validation;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemCreateDto {

    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String name;

    private String description;

    @NotNull(message = "가격은 반드시 입력해야 합니다.")
    @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
    private int price;

    public ItemCreateDto(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
