package com.example.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
List<Product> findAllByCategory_Id(int id);
}
