package com.intuit.craft.controllers;

import com.intuit.craft.data.BusinessProfileData;
import com.intuit.craft.entities.BusinessProfile;
import com.intuit.craft.services.BusinessProfileService;
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

import javax.validation.Valid;
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
    public BusinessProfile postBusinessProfile(@Valid @RequestBody BusinessProfileData businessProfile) {
        return businessProfileService.postBusinessProfile(businessProfile);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BusinessProfile putBusinessProfile(@PathVariable String id,@Valid @RequestBody BusinessProfileData businessProfile) {
        return businessProfileService.update(id, businessProfile);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBusinessProfile(@PathVariable String id) {
        businessProfileService.deleteBusinessProfile(id);
    }

}
