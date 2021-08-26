package com.intuit.craft.services;

import com.intuit.craft.data.BusinessProfileData;
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

    BusinessProfile businessProfileMapper(BusinessProfileData businessProfileData){
        BusinessProfile businessProfile = BusinessProfile.builder().companyName(businessProfileData.getCompanyName())
                .businessAddress(businessProfileData.getBusinessAddress())
                .legalAddress(businessProfileData.getLegalAddress())
                .email(businessProfileData.getEmail())
                .taxIdentifier(businessProfileData.getTaxIdentifier())
                .website(businessProfileData.getWebsite())
                .LegalName(businessProfileData.getLegalName())
                .build();
        businessProfile.setProducts(businessProfileData.getProducts()
                .stream()
                .map(x-> productService.getProductById(x))
                .collect(Collectors.toList()));
        return businessProfile;

    }

    public List<BusinessProfile> getAllBusinessProfiles() {
        return businessProfileRepository.findAll();
    }

    public BusinessProfile postBusinessProfile(BusinessProfileData businessProfileData){
        BusinessProfile businessProfile = this.businessProfileMapper(businessProfileData);
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

    public BusinessProfile update(BusinessProfileData businessProfileData) {
        BusinessProfile businessProfile = this.businessProfileMapper(businessProfileData);
        if (businessProfile.getId() == null || businessProfileRepository.getById(businessProfile.getId())== null) {
            throw new RuntimeException("Bad Request");
        }
        return businessProfileRepository.save(businessProfile);
    }

}
