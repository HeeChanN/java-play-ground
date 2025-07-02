package com.example.practice_rest_docs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(ItemController.class)
@SuppressWarnings("NonAsciiCharacters")
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ItemService itemService;

    private Item item;
    private ItemCreateDto itemCreateDto;

    @BeforeEach
    void setUp() throws Exception {
        itemCreateDto = new ItemCreateDto("name", "description", 10000);
        item = Item.builder()
                .id(1L)
                .name(itemCreateDto.getName())
                .description(itemCreateDto.getDescription())
                .price(itemCreateDto.getPrice())
                .build();
    }

    @Test
    void 아이템_생성() throws Exception{
        //given
        given(itemService.createItem(any(ItemCreateDto.class))).willReturn(item);

        //when & then
        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itemCreateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(itemCreateDto.getName()))
                .andExpect(jsonPath("$.description").value(itemCreateDto.getDescription()))
                .andExpect(jsonPath("$.price").value(itemCreateDto.getPrice()));
    }

    @Test
    void 아이템_단일_조회() throws Exception{
        //given
        given(itemService.getItem(eq(1L))).willReturn(item);

        //when & then
        mockMvc.perform(get("/api/items/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(itemCreateDto.getName()))
                .andExpect(jsonPath("$.description").value(itemCreateDto.getDescription()))
                .andExpect(jsonPath("$.price").value(itemCreateDto.getPrice()));
    }
}
