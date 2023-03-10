package com.example.ecommerce.Service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ecommerce.Repository.Repo;
import com.example.ecommerce.model.Category;
@Service
public class ser {
    @Autowired
     Repo rep;
public List<Category> getAll(){
    return rep.findAll();
}
    public void sabe(Category s){
        rep.save(s);
    }
    public void removebyid(int id){
    rep.deleteById(id);
    }
    public Optional<Category> getcategorybyid(int id){
    return rep.findById(id);
    }

}
