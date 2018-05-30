package com.example.mongoexample.repositories;

import org.springframework.data.mongodb.core.MongoTemplate;

public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    private MongoTemplate mongoTemplate;

    public CustomOrderRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void bestSellingProducts() {

    }

}
