package com.example.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ecommerce.model.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface Repo extends JpaRepository<Category,Integer> {
}
