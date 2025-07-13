package com.example.practice_validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        mockMvc.perform(post("/api/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemCreateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value(itemCreateDto.getName()))
                .andExpect(jsonPath("$.description").value(itemCreateDto.getDescription()))
                .andExpect(jsonPath("$.price").value(itemCreateDto.getPrice()));
    }

    @Test
    void 아이템_생성_리스트_Valid() throws Exception{
        ItemCreateDto errorRequest = new ItemCreateDto("", "description", 10000);

        //when & then
        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(errorRequest))))
                .andExpect(result ->
                        assertInstanceOf(HandlerMethodValidationException.class, result.getResolvedException()));
    }

    @Test
    void 아이템_생성_DTO_Valid() throws Exception{
        ItemCreateDto errorRequest = new ItemCreateDto("name", "description", -10000);

        //when & then
        mockMvc.perform(post("/api/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(errorRequest)))
                .andExpect(result ->
                        assertInstanceOf(MethodArgumentNotValidException.class, result.getResolvedException()));
    }

    @Test
    void 아이템_Path_조회_예외() throws Exception{
        //when & then
        mockMvc.perform(get("/api/items/{id}",0)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result ->
                        assertInstanceOf(HandlerMethodValidationException.class, result.getResolvedException()));
    }

    @Test
    void 아이템_Param_조회_예외() throws Exception{
        //when & then
        mockMvc.perform(get("/api/items")
                        .param("id", "0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(result ->
                        assertInstanceOf(HandlerMethodValidationException.class, result.getResolvedException()));
    }

    @Test
    void 아이템_생성_DTO_Param_동시_Valid() throws Exception{
        ItemCreateDto errorRequest = new ItemCreateDto("name", "description", -10000);

        //when & then
        mockMvc.perform(post("/api/item2")
                        .param("id","0")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(errorRequest)))
                .andExpect(result ->
                        assertInstanceOf(HandlerMethodValidationException.class, result.getResolvedException()));
    }
}
