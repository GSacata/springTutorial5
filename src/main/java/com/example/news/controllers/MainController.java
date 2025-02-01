package com.example.news.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class MainController {
    
    @GetMapping("")
    public String Greetings() {
        return """
              Welcome to the news page!  
            """;
    }
}
