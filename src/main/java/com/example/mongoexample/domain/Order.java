package com.example.mongoexample.domain;

import com.example.mongoexample.annotations.CascadeSave;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Slf4j
@Document
public class Order {

    @Id
    private String id;
    @DBRef
    @CascadeSave
    private Product product;
    private int count;
    private String userId;
}
