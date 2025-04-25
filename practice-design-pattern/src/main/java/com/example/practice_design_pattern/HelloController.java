package com.example.practice_design_pattern;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private CustomLogger logger = CustomLogger.getInstance();

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name) {
        logger.log(name);
        return "Hello " + name;
    }
}
