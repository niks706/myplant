package com.example.ecommerce.global;

import com.example.ecommerce.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalDara {
    public static List<Product> cart;
    static{
        cart=new ArrayList<Product>();
    }
}
