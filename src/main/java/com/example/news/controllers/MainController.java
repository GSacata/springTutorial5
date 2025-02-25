package com.example.news.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class MainController {
    
    @GetMapping("")
    public String Greetings() {
        return """
              Welcome to the news page!  
            """;
    }

    @GetMapping("/hello-world")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Bem-vindo ao nosso site");
        return "hello-world.html";
    }
}
