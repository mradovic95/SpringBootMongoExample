package com.example.mongoexample.controllers.controllers;

import com.example.mongoexample.domain.Order;
import com.example.mongoexample.services.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@Api(description = "order api")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "Get list of all Orders", notes = "Some notes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping
    public ResponseEntity<Page<Order>> getAll(Pageable pageable) {
        return new ResponseEntity<>(orderService.getAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get single Order by id", notes = "Some notes")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getById(@PathVariable String id) {
        return new ResponseEntity(orderService.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create order", notes = "Some notes")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody Order order) {
        orderService.add(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete order", notes = "Some notes")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        orderService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Update order", notes = "Some notes")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Order order) {
        orderService.update(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
