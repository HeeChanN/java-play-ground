package com.example.practice_rest_docs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
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
@AutoConfigureRestDocs
@Import(CustomRestDocsConfig.class)
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
                .andExpect(jsonPath("$.price").value(itemCreateDto.getPrice()))
                .andDo(ItemDoc.createItem());
    }

    @Test
    void 아이템_생성시_이름_공백_검증() throws Exception{
        ItemCreateDto errorRequest = new ItemCreateDto("", "description", 10000);

        //when & then
        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(errorRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message").value("이름은 공백일 수 없습니다."))
                .andDo(ItemDoc.errorSnippet("items/create-item/blank-name"));
    }

    @Test
    void 아이템_생성시_가격이_음수일_경우_검증() throws Exception{
        ItemCreateDto errorRequest = new ItemCreateDto("name", "description", -10000);

        //when & then
        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(errorRequest)))
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message").value("가격은 0 이상이어야 합니다."))
                .andDo(ItemDoc.errorSnippet("items/create-item/minus-price"));
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
                .andExpect(jsonPath("$.price").value(itemCreateDto.getPrice()))
                .andDo(ItemDoc.getItem());
    }
}
