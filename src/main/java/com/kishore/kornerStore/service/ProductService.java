package com.kishore.kornerStore.service;

import com.kishore.kornerStore.model.Product;
import com.kishore.kornerStore.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Product getProduct(int id){
        return productRepo.findById(id).orElse(null);
    }

    public void addProduct(Product product){
        productRepo.save(product);
    }

    public Product updateProduct(int id, Product product){
        if(productRepo.findById(id).orElse(null)!=null){
            productRepo.save(product);
            return product;
        }else{
            return null;
        }
    }

    public boolean deleteProduct(int id){
        if(productRepo.findById(id).orElse(null)!=null){
            productRepo.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public List<Product> searchProducts(String keyword){
        return productRepo.searchProducts(keyword);
    }
}
