package com.intuit.craft.controllers;

import com.intuit.craft.entities.BusinessProfile;
import com.intuit.craft.entities.Product;
import com.intuit.craft.services.BusinessProfileService;
import com.intuit.craft.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("businessProfile")
public class BusinessProfileController {

    @Autowired BusinessProfileService businessProfileService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<BusinessProfile> getAllBusinessProfiles() {
        return businessProfileService.getAllBusinessProfiles();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BusinessProfile getBusinessProfileById(@PathVariable String id) {
        return businessProfileService.getBusinessProfileById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessProfile postBusinessProfile(@RequestBody BusinessProfile businessProfile) {
        return businessProfileService.postBusinessProfile(businessProfile);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public BusinessProfile putBusinessProfile(@RequestBody BusinessProfile businessProfile) {
        return businessProfileService.update(businessProfile);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBusinessProfile(@PathVariable String id) {
        businessProfileService.deleteBusinessProfile(id);
    }

}
