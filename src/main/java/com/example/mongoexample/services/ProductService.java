package com.example.mongoexample.services;

import com.example.mongoexample.domain.Product;
import com.example.mongoexample.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAll(Pageable pageable) {
        log.info("Getting all products");
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getById(String id) {
        log.info("Getting product with id: {}", id);
        return productRepository.findById(id);
    }

    public void add(Product product) {
        log.info("Adding product: {}", product);
        productRepository.insert(product);
    }

    public void deleteById(String id) {
        log.info("Deleting product with id: {}", id);
        productRepository.deleteById(id);
    }

    public void update(Product product) {
        log.info("Updating product: {}", product);
        productRepository.save(product);
    }

}
