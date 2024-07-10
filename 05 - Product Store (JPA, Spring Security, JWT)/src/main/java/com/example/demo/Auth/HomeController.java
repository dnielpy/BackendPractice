package com.example.demo.Auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/change-password")
    public String changePassword() {
        return "change-password";
    }

    @RequestMapping("/product")
    public String product() {
        return "product";
    }

    @RequestMapping("/account")
    public String account() {
        return "account";
    }

    @RequestMapping("/orders")
    public String orders() {
        return "orders";
    }

    @RequestMapping("/shop")
    public String shop() {
        return "shop";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/refund")
    public String refund() {
        return "refund";
    }

    @RequestMapping("/terms")
    public String terms() {
        return "terms";
    }

    @RequestMapping("/cart")
    public String cart() {
        return "cart";
    }

    @RequestMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

}