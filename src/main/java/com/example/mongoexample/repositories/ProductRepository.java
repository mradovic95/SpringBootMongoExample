package com.example.mongoexample.repositories;

import com.example.mongoexample.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {



}
