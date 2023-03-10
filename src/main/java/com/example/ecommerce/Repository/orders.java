package com.example.ecommerce.Repository;
import com.example.ecommerce.model.orderplaced;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
@Repository
public interface orders extends  JpaRepository<orderplaced,Integer>{
}
