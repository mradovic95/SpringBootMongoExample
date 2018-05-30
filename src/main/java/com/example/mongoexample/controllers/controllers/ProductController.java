package com.example.mongoexample.controllers.controllers;

import com.example.mongoexample.domain.Product;
import com.example.mongoexample.services.ProductService;
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
@RequestMapping("/product")
@Api(description = "product api")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Get list of all Products", notes = "Some notes")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "Number of items to return", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "What page number you want", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping
    public ResponseEntity<Page<Product>> getAll(Pageable pageable) {
        return new ResponseEntity<>(productService.getAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get single Product by id", notes = "Some notes")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getById(@PathVariable String id) {
        return new ResponseEntity(productService.getById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Create product", notes = "Some notes")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody Product product) {
        productService.add(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete product", notes = "Some notes")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Update product", notes = "Some notes")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Product product) {
        productService.update(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
