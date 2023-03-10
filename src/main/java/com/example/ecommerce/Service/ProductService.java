package com.example.ecommerce.Service;

import com.example.ecommerce.Repository.ProductRepo;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo repository;
    public List<Product> getAllproduct(){

        return repository.findAll();
    }
    public void save(Product pro){

        repository.save(pro);
    }
    public void remove(long id){
        repository.deleteById(id);
    }
    public Optional<Product> getproductbyid(long id){
        return repository.findById(id);
    }
    public List<Product> getallproductbyCatid(int id){
        return  repository.findAllByCategory_Id(id);
    }
}
