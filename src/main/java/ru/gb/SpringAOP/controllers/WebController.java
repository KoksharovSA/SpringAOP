package ru.gb.SpringAOP.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.SpringAOP.models.Product;
import ru.gb.SpringAOP.models.Purchase;
import ru.gb.SpringAOP.services.ProductService;
import ru.gb.SpringAOP.services.PurchaseService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {
    private final ProductService productService;
    private final PurchaseService purchaseService;
    @GetMapping("/publicData")
    public String publicPage(){
        return "publicData";
    }

    @GetMapping("/adminPanel")
    public String adminPanel(Model model){
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "adminPanel";
    }

    @GetMapping("/personalAccount")
    public String personalAccount(Model model){
        List<Product> products = purchaseService.getAllProductUser();
        model.addAttribute("products", products);
        return "personalAccount";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model){
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "loginPage";
    }

    public String index(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getName());
        return "index";
    }

    @GetMapping("/index")
    public String mainPageIndex(Model model){
        return index(model);
    }

    @GetMapping("/")
    public String mainPage(Model model){
        return index(model);
    }

    @PostMapping("/addProduct")
    public String addProduct(Product product, Model model){
        productService.addProduct(product);
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "adminPanel";
    }

    @PostMapping("/buy/{id}")
    public String buyProduct(@PathVariable("id") Long id){
        Purchase purchase = new Purchase();
        purchase.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        purchase.setProduct(productService.getProductById(id));
        purchaseService.addPurchase(purchase);
        return "redirect:/index";
    }
}
