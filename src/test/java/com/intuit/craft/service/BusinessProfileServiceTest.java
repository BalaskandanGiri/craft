package com.intuit.craft.service;

import com.intuit.craft.data.validate;
import com.intuit.craft.entities.BusinessProfile;
import com.intuit.craft.entities.Product;
import com.intuit.craft.repositories.BusinessProfileRepository;
import com.intuit.craft.repositories.ProductRepository;
import com.intuit.craft.services.BusinessProfileService;
import com.intuit.craft.services.ProductService;
import com.intuit.craft.services.ValidateProduct;
import com.intuit.craft.util.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BusinessProfileServiceTest {

    @InjectMocks
    BusinessProfileService service = new BusinessProfileService();

    @Mock
    BusinessProfileRepository businessProfileRepository;
    @Mock
    ProductService productService;

    @Mock
    RestTemplate restTemplate;

    @Test
    @DisplayName("create Business Profile")
    void postBusinessProfile() {
        when(businessProfileRepository.save(Mockito.any())).thenReturn(TestUtils.getBusinessProfile());
        when(productService.getProductById("1")).thenReturn(TestUtils.getProducts().get(0));
        when(productService.getProductById("2")).thenReturn(TestUtils.getProducts().get(1));
//        when(restTemplate.getForObject(Mockito.any(), Mockito.any())).thenReturn(new validate(false));
//        when(productService.validate(Mockito.any())).thenReturn(false);
        BusinessProfile businessProfile = service.postBusinessProfile(TestUtils.getBusinessProfileData());
        assertNotNull(businessProfile);
    }


    @Test
    @DisplayName("get Business Profile by id")
    void getProductById() {
        when(businessProfileRepository.getById(Mockito.any())).thenReturn(TestUtils.getBusinessProfile());
        BusinessProfile businessProfile = service.getBusinessProfileById("1");
        assertNotNull(businessProfile);
        assertEquals(businessProfile.getId(), "1");
    }

    @Test
    @DisplayName("get all Business Profile")
    void getProducts() {
        when(businessProfileRepository.findAll()).thenReturn(TestUtils.getBusinessProfiles());
        List<BusinessProfile> businessProfiles = service.getAllBusinessProfiles();
        assertNotNull(businessProfiles);
        assertEquals(businessProfiles.size(), 2);
    }


    @Test
    @DisplayName("update Business Profile")
    void updateProduct() {
        when(businessProfileRepository.save(Mockito.any())).thenReturn(TestUtils.getBusinessProfile());
        when(businessProfileRepository.getById(Mockito.any())).thenReturn(TestUtils.getBusinessProfile());
        BusinessProfile businessProfile = service.update("1",TestUtils.getBusinessProfileData());
        assertNotNull(businessProfile);
        assertEquals(businessProfile.getId(), "1");
    }
}
