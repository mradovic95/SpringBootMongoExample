package com.example.mongoexample.services;

import com.example.mongoexample.domain.Order;
import com.example.mongoexample.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<Order> getAll(Pageable pageable) {
        log.info("Getting all orders");
        return orderRepository.findAll(pageable);
    }

    public Optional<Order> getById(String id) {
        log.info("Getting order with id: {}", id);
        return orderRepository.findById(id);
    }

    public void add(Order order) {
        log.info("Adding order: {}", order);
        orderRepository.insert(order);
    }

    public void deleteById(String id) {
        log.info("Deleting order with id: {}", id);
        orderRepository.deleteById(id);
    }

    public void update(Order order) {
        log.info("Updating order: {}", order);
        orderRepository.save(order);
    }

}
