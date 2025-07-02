package com.example.practice_rest_docs;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemCreateDto {
    private String name;
    private String description;
    private int price;

    public ItemCreateDto(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
