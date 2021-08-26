package com.intuit.craft.util;

import com.intuit.craft.data.BusinessProfileData;
import com.intuit.craft.data.ProductData;
import com.intuit.craft.entities.Address;
import com.intuit.craft.entities.BusinessProfile;
import com.intuit.craft.entities.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static Product getProduct() {
        Product product = new Product();
        product.setId("123");
        product.setName("product1");
        product.setDescription("desc for product 1");
        return product;
    }

    public static List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        products.add(getProduct());
        Product product2 = new Product();
        product2.setId("234");
        product2.setName("product2");
        product2.setDescription("desc for product 2");
        products.add(product2);
        return products;
    }

    public static ProductData getProductData() {
        ProductData productData = new ProductData();
        productData.setName("product1");
        productData.setDescription("desc for product 1");
        return productData;
    }

    public static BusinessProfile getBusinessProfile() {
        BusinessProfile businessProfile = new BusinessProfile();
        businessProfile.setId("1");
        businessProfile.setCompanyName("company1");
        businessProfile.setLegalName("company1");
        businessProfile.setEmail("company1@gmail.com");
        businessProfile.setWebsite("company1.com");
        businessProfile.setTaxIdentifier("123.abc");
        businessProfile.setBusinessAddress(new Address());
        businessProfile.setLegalAddress(new Address());
        businessProfile.setProducts(TestUtils.getProducts());
        return businessProfile;
    }

    public static List<BusinessProfile> getBusinessProfiles() {
        List<BusinessProfile> businessProfiles = new ArrayList<>();
        businessProfiles.add(getBusinessProfile());
        BusinessProfile businessProfile  = new BusinessProfile();
        businessProfile.setId("1");
        businessProfile.setCompanyName("company1");
        businessProfile.setLegalName("company1");
        businessProfile.setEmail("company1@gmail.com");
        businessProfile.setWebsite("company1.com");
        businessProfile.setTaxIdentifier("123.abc");
        businessProfile.setBusinessAddress(new Address());
        businessProfile.setLegalAddress(new Address());
        businessProfile.setProducts(TestUtils.getProducts());
        businessProfiles.add(businessProfile);
        return businessProfiles;
    }

    public static BusinessProfileData getBusinessProfileData() {
        BusinessProfileData businessProfile= new BusinessProfileData();
        businessProfile.setCompanyName("company1");
        businessProfile.setLegalName("company1");
        businessProfile.setEmail("company1@gmail.com");
        businessProfile.setWebsite("company1.com");
        businessProfile.setTaxIdentifier("123.abc");
        businessProfile.setBusinessAddress(new Address());
        businessProfile.setLegalAddress(new Address());
        businessProfile.setProducts(Arrays.asList("1","2"));
        return businessProfile;
    }


}
