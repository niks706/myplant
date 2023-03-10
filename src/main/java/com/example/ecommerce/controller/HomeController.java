package com.example.ecommerce.controller;

import com.example.ecommerce.global.GlobalDara;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.ecommerce.Service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    ser categoryService;
    @Autowired
    ProductService productService;
    @GetMapping({"/","/home"})
    public String home(Model model){
        model.addAttribute("cartCount", GlobalDara.cart.size());
        return "index.html";

    }
    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("cartCount",GlobalDara.cart.size());
       model.addAttribute("categories",categoryService.getAll());
       model.addAttribute("products",productService.getAllproduct());
       return "shop.html";
    }
    @GetMapping("/shop/category/{id}")
     public String shopbycategory(Model model, @PathVariable int id){
        model.addAttribute("categories",categoryService.getAll());
        model.addAttribute("cartCount",GlobalDara.cart.size());
        model.addAttribute("products",productService.getallproductbyCatid(id));
        return "shop.html";
    }
    @GetMapping("/shop/viewproduct/{id}")
    public String viewproduct(Model model, @PathVariable int id){
       // model.addAttribute("categories",categoryService.getAll());
        model.addAttribute("cartCount",GlobalDara.cart.size());
        model.addAttribute("product",productService.getproductbyid(id).get());
        return "viewProduct.html";
    }
}
