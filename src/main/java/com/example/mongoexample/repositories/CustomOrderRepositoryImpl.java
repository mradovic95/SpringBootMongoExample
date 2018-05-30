package com.example.mongoexample.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;

public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    private MongoTemplate mongoTemplate;

    public CustomOrderRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void bestCustomers() {

        GroupOperation groupOperation = Aggregation.group("userId")
                .sum("count").as("count");
        SortOperation sortByPopDesc = sort(new Sort(Sort.Direction.DESC, "count"));
        Aggregation aggregation = Aggregation.newAggregation(groupOperation, sortByPopDesc);
        AggregationResults results = mongoTemplate.aggregate(aggregation, "order", Object.class);

        for (Object object : results.getMappedResults()) {
            System.out.println(object);
        }

    }

}
