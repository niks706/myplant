package com.example.ecommerce.controller;

import com.example.ecommerce.Service.order_service;
import com.example.ecommerce.model.orderplaced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/api/v1/payment"})
public class paymentcontroller {
    @Autowired
    order_service orderService;
    @PostMapping("/payNow")
    public String checkout(@ModelAttribute orderplaced order){
        orderService.save(order);

        return "shop.html";
    }
}
