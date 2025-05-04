package com.example.practice_spring.ex4;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    // 1. LocalDateTime.now() + request 로 받아오기 테스트
    @PostMapping("/check/time")
    public ItemResDto create(@RequestBody ItemCreateReqDto itemCreateReqDto){
        System.out.println(itemCreateReqDto.getFinishedAt());
        Item item = new Item(LocalDateTime.now(), itemCreateReqDto.getFinishedAt());
        Item stored = itemRepository.save(item);
        System.out.println(item.getCreatedAt() + " " + itemCreateReqDto.getFinishedAt());
        System.out.println(stored.getCreatedAt() + " " + itemCreateReqDto.getFinishedAt());

        return new ItemResDto(item.getCreatedAt(),item.getFinishedAt());
    }
}
