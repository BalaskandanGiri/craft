package com.intuit.craft.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.intuit.craft.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository{

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Product save(Product product) {
        dynamoDBMapper.save(product);
        return product;
    }

    public List<Product> findAll() {
        final PaginatedScanList<Product> scan = dynamoDBMapper
                .scan(Product.class, new DynamoDBScanExpression());
        return scan.parallelStream().collect(Collectors.toList());
    }

    public Product getById(String id) {
        final Product product = Product.builder().id(id).build();
        return dynamoDBMapper.load(product);
    }

    public void delete(String id) {
        dynamoDBMapper.delete(Product.builder().id(id).build());
    }
}