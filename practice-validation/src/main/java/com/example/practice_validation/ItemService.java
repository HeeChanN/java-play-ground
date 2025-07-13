package com.example.practice_validation;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ItemService {

    private ConcurrentHashMap<Long, Item> items = new ConcurrentHashMap<>();
    private AtomicLong seq = new AtomicLong(0);

    public Item createItem(ItemCreateDto itemCreateDto) {
        Item item = Item.builder()
                .id(seq.incrementAndGet())
                .name(itemCreateDto.getName())
                .description(itemCreateDto.getDescription())
                .price(itemCreateDto.getPrice())
                .build();
        items.put(item.getId(), item);
        return item;
    }

    public Item getItem(long id) {
        Item item = items.get(id);
        if (item == null) {
            throw new RuntimeException("Item not found with id: " + id);
        }
        return item;
    }
}
