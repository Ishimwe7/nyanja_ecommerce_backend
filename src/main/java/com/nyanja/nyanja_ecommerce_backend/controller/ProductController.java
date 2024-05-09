package com.nyanja.nyanja_ecommerce_backend.controller;

import com.nyanja.nyanja_ecommerce_backend.model.Product;
import com.nyanja.nyanja_ecommerce_backend.services.ProductService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private static final String UPLOAD_DIR = "./images/";
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @GetMapping("/allProducts")
    public ResponseEntity<?> getAllProducts() {
        try {
            //List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(productService.getAllProducts());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
         }

    // Get product by ID
    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            if (product!=null) {
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
         }

    // Add new product (Admin)
    @PostMapping("/newProduct")
    public ResponseEntity<?> addProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("quantity") int quantity,
            @RequestParam("category") String category,
            @RequestParam("product_image") MultipartFile file
            ) {
        try {
            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setCategory(category);
            product.setQuantity(quantity);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
            Files.write(path, bytes);
            product.setImagePath(path.toString());

            Product savedProduct = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product Added Successfully !");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    // Update product (Admin)
    @PutMapping("/editProduct/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        try {
            Product existingProduct = productService.getProductById(productId);

            if (existingProduct!=null) {
                // Update the fields of the existing product
                existingProduct.setName(product.getName());
                existingProduct.setCategory(product.getCategory());
                existingProduct.setDescription(product.getDescription());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setQuantity(product.getQuantity());

                Product updatedProduct = productService.updateProduct(existingProduct.getId(),existingProduct);
                if(updatedProduct!=null) {
                    return ResponseEntity.ok(updatedProduct);
                }
                else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Updating Product Failed !!");
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    // Delete product(Admin)
    @DeleteMapping("deleteProduct/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok("Product with ID " + productId + " has been deleted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
