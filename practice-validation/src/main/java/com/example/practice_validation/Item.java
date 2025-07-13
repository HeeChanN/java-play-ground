package com.example.practice_validation;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Item {

    private long id;
    private String name;
    private String description;
    private int price;

    @Builder
    public Item(long id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
