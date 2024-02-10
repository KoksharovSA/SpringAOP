package ru.gb.SpringAOP.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.SpringAOP.models.Product;
import ru.gb.SpringAOP.services.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final ProductService productService;
    @GetMapping("/publicData")
    public String publicPage(){
        return "publicData";
    }

    @GetMapping("/adminPanel")
    public String adminPanel(){
        return "adminPanel";
    }
    @GetMapping("/loginPage")
    public String loginPage(){
        return "loginPage";
    }

    @GetMapping("/index")
    public String index(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping("/addProduct")
    public String addProduct(Product product, Model model){
        productService.addProduct(product);
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "adminPanel";
    }
}
