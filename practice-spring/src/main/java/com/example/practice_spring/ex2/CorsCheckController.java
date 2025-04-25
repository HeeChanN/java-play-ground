package com.example.practice_spring.ex2;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class CorsCheckController {

    @GetMapping
    public String getTest() {
        return "GET OK";
    }
}
