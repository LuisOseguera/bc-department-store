package com.luis.bcdepartmentstore.controller;

import com.luis.bcdepartmentstore.model.Product;
import com.luis.bcdepartmentstore.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(this.productRepository.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity getOneProduct(@PathVariable Long id) {
        return ResponseEntity.ok(this.productRepository.findById(id).get());
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody Product product) {
        return ResponseEntity.status(201).body(this.productRepository.save(product));
    }

    @PutMapping("{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product product) {

        Product productToUpdate = this.productRepository.findById(id).get();

        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setAmount(product.getAmount());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setImagePath(product.getImagePath());

        return ResponseEntity.ok(this.productRepository.save(productToUpdate));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {

        Product product = this.productRepository.findById(id).get();

        this.productRepository.deleteById(id);

        return ResponseEntity.ok(product);
    }
}
