package com.example.ecommerce.Service;

import com.example.ecommerce.Repository.orders;
import com.example.ecommerce.model.orderplaced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class order_service {
    @Autowired
    orders order_repo;
    public void save(orderplaced order){
        order_repo.save(order);

    }
}
