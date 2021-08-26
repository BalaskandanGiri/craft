package com.intuit.craft.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.intuit.craft.entities.BusinessProfile;
import com.intuit.craft.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BusinessProfileRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public BusinessProfile save(BusinessProfile businessProfile) {
        dynamoDBMapper.save(businessProfile);
        return businessProfile;
    }

    public List<BusinessProfile> findAll() {
        final PaginatedScanList<BusinessProfile> scan = dynamoDBMapper
                .scan(BusinessProfile.class, new DynamoDBScanExpression());
        return scan.parallelStream().collect(Collectors.toList());
    }

    public BusinessProfile getById(String id) {
        final BusinessProfile businessProfile = BusinessProfile.builder().id(id).build();
        return dynamoDBMapper.load(businessProfile);
    }


    public void delete(String id) {
        dynamoDBMapper.delete(getById(id));
    }

}