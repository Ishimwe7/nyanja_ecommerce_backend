package com.nyanja.nyanja_ecommerce_backend.services;

import com.nyanja.nyanja_ecommerce_backend.model.Product;
import com.nyanja.nyanja_ecommerce_backend.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductByCategory(String category) {
        return productRepository.findProductsByCategory(category);
    }
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(long id, Product product) {
        Product updatedProduct=null;
        if (productRepository.existsById(id)) {
            product.setId(id);
            updatedProduct = productRepository.save(product);
        }
        return updatedProduct;
    }

    public void deleteProduct(long id) {
       productRepository.deleteById(id);
    }
}
