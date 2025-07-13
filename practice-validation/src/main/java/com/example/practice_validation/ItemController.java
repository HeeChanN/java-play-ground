package com.example.practice_validation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/api/items")
    public Item createItems(@Valid @RequestBody List<ItemCreateDto> itemCreateDto) {
        return itemService.createItem(itemCreateDto.get(0));
    }

    @PostMapping("/api/item")
    public Item createItem(@Valid @RequestBody ItemCreateDto itemCreateDto) {
        return itemService.createItem(itemCreateDto);
    }

    @GetMapping("/api/items/{id}")
    public Item getItemWithPath(@Min(1) @PathVariable(name = "id") long id){
        return itemService.getItem(id);
    }

    @GetMapping("/api/items")
    public Item getItemWithParam(@Min(1) @RequestParam(name = "id") long id){
        return itemService.getItem(id);
    }

    @PostMapping("/api/item2")
    public Item createItemWithParam(@Valid @RequestBody ItemCreateDto itemCreateDto,
                                    @Min(1) @RequestParam(name = "id") long id) {
        return itemService.createItem(itemCreateDto);
    }

}
