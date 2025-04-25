package com.example.practice_spring.ex2;

import com.example.practice_spring.global.config.CorsConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CorsCheckController.class)
@Import(CorsConfig.class)
@AutoConfigureMockMvc
@SuppressWarnings("NonAsciiCharacters")
@ActiveProfiles("test")
public class CorsCheckControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void cors_허용되지_않은_도메인일_경우() throws Exception {
        mockMvc.perform(get("/api/v1/test")
                        .header("Origin", "http://localhost:3001")
                        .header("Access-Control-Request-Method", "POST"))
                .andExpect(status().isForbidden())
                .andExpect(header().doesNotExist("Access-Control-Allow-Origin"))
                .andExpect(header().doesNotExist("Access-Control-Allow-Methods"));
    }

    @Test
    void cors_허용된_도메인일_경우() throws Exception {
        mockMvc.perform(get("/api/v1/test")
                        .header("Origin", "http://localhost:3000")
                        .header("Access-Control-Request-Method", "POST"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:3000"))
                .andExpect(content().string("GET OK"));
    }
}
