package com.intuit.craft.services;

import com.intuit.craft.Exceptions.BadRequest;
import com.intuit.craft.data.BusinessProfileData;
import com.intuit.craft.data.validate;
import com.intuit.craft.entities.BusinessProfile;
import com.intuit.craft.entities.Product;
import com.intuit.craft.repositories.BusinessProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    RestTemplate restTemplate;

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
        return asyncValidate(businessProfile);
    }

    public void deleteBusinessProfile(String id) {
        if (businessProfileRepository.getById(id)== null) {
            throw new BadRequest("No BusinessProfile with id : " + id);
        }
        businessProfileRepository.delete(id);
    }

    public BusinessProfile getBusinessProfileById(String id) {
        BusinessProfile businessProfile = businessProfileRepository.getById(id);
        if (businessProfile == null) {
            throw new BadRequest("No BusinessProfile with id : " + id);
        }
        return businessProfile;
    }

    public BusinessProfile update(String id, BusinessProfileData businessProfileData) {
        BusinessProfile businessProfile = this.businessProfileMapper(businessProfileData);
        businessProfile.setId(id);
        if (businessProfile.getId() == null || businessProfileRepository.getById(businessProfile.getId())== null) {
            throw new BadRequest("No BusinessProfile with id : " + id);
        }
        return asyncValidate(businessProfile);
    }


    private BusinessProfile asyncValidate(BusinessProfile businessProfile) {
        List<Thread> threads = new ArrayList<>();
        for(Product product: businessProfile.getProducts()) {
            Thread thread = new Thread(new ValidateProduct(product));
            thread.start();
            threads.add(thread);
        }
        for(Thread thread: threads) {
            try {
                thread.join();

            }
            catch (InterruptedException e) {
                log.error(e.getMessage());
                throw new BadRequest("Validation failed");
            }
        }
        return businessProfileRepository.save(businessProfile);
    }

}
