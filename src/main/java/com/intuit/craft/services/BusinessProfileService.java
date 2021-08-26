package com.intuit.craft.services;

import com.intuit.craft.entities.BusinessProfile;
import com.intuit.craft.entities.Product;
import com.intuit.craft.repositories.BusinessProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BusinessProfileService {

    @Autowired
    BusinessProfileRepository businessProfileRepository;

    @Autowired
    ProductService productService;

    public List<BusinessProfile> getAllBusinessProfiles() {
        return businessProfileRepository.findAll();
    }

    public BusinessProfile postBusinessProfile(BusinessProfile businessProfile){
        businessProfile.setProducts(businessProfile.getProducts()
                .stream()
                .map(x-> productService.getProductById(x.getId()))
                .collect(Collectors.toList()));
        List<Thread> threads = new ArrayList<>();
        for(Product product: businessProfile.getProducts()) {
            Thread temp = new Thread(new ValidateProduct(product));
            temp.start();
            threads.add(temp);
        }
        for(Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
        return businessProfileRepository.save(businessProfile);
    }

    public void deleteBusinessProfile(String id) {
        businessProfileRepository.delete(id);
    }

    public BusinessProfile getBusinessProfileById(String id) {
        BusinessProfile businessProfile = businessProfileRepository.getById(id);
        if (businessProfile == null) {
            throw new RuntimeException("No Business Profile with id : " + id);
        }
        return businessProfile;
    }

    public BusinessProfile update(BusinessProfile businessProfile) {
        if (businessProfile.getId() == null || businessProfileRepository.getById(businessProfile.getId())== null) {
            throw new RuntimeException("Bad Request");
        }
        return businessProfileRepository.save(businessProfile);
    }

}
