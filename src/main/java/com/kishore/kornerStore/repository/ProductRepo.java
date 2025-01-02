package com.kishore.kornerStore.repository;

import com.kishore.kornerStore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer> {
}