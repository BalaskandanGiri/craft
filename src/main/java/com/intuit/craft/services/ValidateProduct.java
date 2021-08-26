package com.intuit.craft.services;

import com.intuit.craft.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidateProduct implements Runnable {

    private final int MAX_RETRIES = 3;

    @Autowired
    ProductService productService;

    private int currentRetries = 0;
    private Product product = null;

    public ValidateProduct(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        while(true) {
            if (this.currentRetries >=  MAX_RETRIES) {
                throw new RuntimeException("Validation failed for product " + product.getName() + ".");
            }
            if(productService.validate(this.product)) {
                return;
            }
            this.currentRetries += 1;
        }
    }
}
