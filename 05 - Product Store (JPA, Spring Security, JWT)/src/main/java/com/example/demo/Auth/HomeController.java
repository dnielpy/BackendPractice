package com.example.demo.Auth;

import com.example.demo.Product.ProductDTO;
import com.example.demo.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/index")
    public String index2() {
        return index();
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

    @Autowired
    private ProductService productService;

    @GetMapping("/shop")
    public String shop(Model model) {
        List<ProductDTO> products = productService.getNineRandomProducts();
        model.addAttribute("products", products);
        return "shop";
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