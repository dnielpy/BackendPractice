package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {return "about";}

    @GetMapping("/index")
    public String projects() {
        return "projects";
    }

    @GetMapping("/about")
    public String contact() {return "contact";}
}