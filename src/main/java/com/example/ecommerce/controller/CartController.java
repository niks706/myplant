package com.example.ecommerce.controller;

//import ch.qos.logback.core.ui.Model;
import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.global.GlobalDara;
import com.example.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalDara.cart.add(productService.getproductbyid(id).get());
        return "redirect:/shop";
    }
    @GetMapping("/cart")
    public String seeCart(Model model){
      model.addAttribute("carCount",GlobalDara.cart.size());
      model.addAttribute("total",GlobalDara.cart.stream().mapToDouble(Product::getPrice).sum());
    model.addAttribute("cart",GlobalDara.cart);
    return "cart.html";
    }
    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index){
        GlobalDara.cart.remove(index);
        return "redirect:/cart";

    }
    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("total",GlobalDara.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout.html";
    }
}
