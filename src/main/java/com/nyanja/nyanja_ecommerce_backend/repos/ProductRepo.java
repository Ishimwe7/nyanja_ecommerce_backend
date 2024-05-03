package com.nyanja.nyanja_ecommerce_backend.repos;

import com.nyanja.nyanja_ecommerce_backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findProductsByCategory(String category);
}
