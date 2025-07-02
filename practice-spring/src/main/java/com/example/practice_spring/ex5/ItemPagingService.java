package com.example.practice_spring.ex5;


import com.example.practice_spring.ex4.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemPagingService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public void findAll(int page, int size) {

    }
}
