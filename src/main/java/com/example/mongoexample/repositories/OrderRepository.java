package com.example.mongoexample.repositories;

import com.example.mongoexample.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String>, CustomOrderRepository {


}
