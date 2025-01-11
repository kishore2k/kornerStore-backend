package com.kishore.kornerStore.controller;

import com.kishore.kornerStore.model.Product;
import com.kishore.kornerStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product response =  productService.getProduct(id);
        try{
            if(response==null){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        try{
            productService.addProduct(product);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }catch(Exception e){
            System.out.println("Error: "+e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product ){
        try{
            Product updated_product = productService.updateProduct(id,product);
            if(updated_product==null) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(updated_product,HttpStatus.OK);
        }catch(Exception e){
            System.out.println("Error: "+e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        try{
            if(!productService.deleteProduct(id)) {
                return new ResponseEntity<>("Failure",HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Success",HttpStatus.OK);
        }catch(Exception e){
            System.out.println("Error: "+e);
            return new ResponseEntity<>("Failed to Delete.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
