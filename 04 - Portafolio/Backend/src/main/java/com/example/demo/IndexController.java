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

    @GetMapping("/projects")
    public String projects() {
        return "projects-masonry";
    }

    @GetMapping("/contact")
    public String contact() {return "contact";}
}