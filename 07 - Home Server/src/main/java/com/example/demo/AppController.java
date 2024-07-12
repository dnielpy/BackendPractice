package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {
    @GetMapping("/path")
    public String path() {
        return "path";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
