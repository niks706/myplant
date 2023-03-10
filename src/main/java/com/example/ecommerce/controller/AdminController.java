package com.example.ecommerce.controller;

import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.Service.ser;
import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Controller
public class AdminController {
    //public static String directory=System.getProperty("user.dir")+
    @Autowired
    ser catservice;
    @Autowired
    ProductService productservice;

    @RequestMapping(value = {"/admin"})
    public String hom() {
        return "index.html";
    }

    @GetMapping("/admin/categories")
    public String getcategories(Model model) {
        model.addAttribute("categories", catservice.getAll());
        return "categories.html";
    }

    @GetMapping("/admin/categories/add")
    public String get(Model model) {
        model.addAttribute("category", new Category());
        return "categoriesAdd.html";
    }

    @PostMapping("/admin/categories/add")
    public String post(@ModelAttribute("category") Category cate) {
        catservice.sabe(cate);

        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String delete(@PathVariable int id) {
        catservice.removebyid(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String update(@PathVariable int id, Model model) {
        Optional<Category> cat = catservice.getcategorybyid(id);
        if (cat.isPresent()) {
            model.addAttribute("category", cat.get());
            return "categoriesAdd.html";
        } else {
            return "404";
        }

    }

    //product section
    @GetMapping("/admin/products")
    public String products(Model model) {
        model.addAttribute("products", productservice.getAllproduct());
        return "products.html";
    }

    @RequestMapping("/admin/products/add")
    public String productsAdd(Model model) {
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", catservice.getAll());
        return "productsAdd.html";
    }

    @PostMapping("/admin/products/add")
    public String productAddpost(@ModelAttribute("productDTO") ProductDTO productDTO, @RequestParam("productImage") MultipartFile file, @RequestParam(value = "imgName", required = false) String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(catservice.getcategorybyid(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight((productDTO.getWeight()));
        product.setDescription((productDTO.getDescription()));

        File savefile = new File("/Users/nikhilshrivastava/Downloads/ecommerce/src/main/resources/static/productImages");
        if (!file.isEmpty()) {
            Path path = Paths.get(savefile.getAbsolutePath() + File.separator + file.getOriginalFilename());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            product.setImageName(file.getOriginalFilename());
        } else {
            product.setImageName(imgName);
        }
        productservice.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String productdelete(@PathVariable long id) {
        productservice.remove(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String productupdate(@PathVariable long id, Model model) {
        Product product = productservice.getproductbyid(id).get();
        ProductDTO productdto = new ProductDTO();
        productdto.setId(product.getId());
        productdto.setName(product.getName());
        productdto.setCategoryId(product.getCategory().getId());
        productdto.setPrice(product.getPrice());
        productdto.setWeight(product.getWeight());
        productdto.setDescription(product.getDescription());
        productdto.setImageName(product.getImageName());
        model.addAttribute("categories", catservice.getAll());
        model.addAttribute("productDTO", productdto);
        return "productsAdd";
    }


}
