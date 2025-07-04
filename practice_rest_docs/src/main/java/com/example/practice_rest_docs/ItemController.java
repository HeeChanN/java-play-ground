package com.example.practice_rest_docs;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/api/items")
    public Item createItem(@Valid @RequestBody ItemCreateDto itemCreateDto) {
        return itemService.createItem(itemCreateDto);
    }

    @GetMapping("/api/items/{id}")
    public Item getItem(@PathVariable(name = "id") long id){
        return itemService.getItem(id);
    }
}
