package com.intuit.craft.services;

import com.intuit.craft.Exceptions.BadRequest;
import com.intuit.craft.data.validate;
import com.intuit.craft.entities.Product;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@Data
public class ValidateProduct implements Runnable{

    private final int MAX_RETRIES = 3;

    @Autowired
    ProductService productService;

    Thread thread;
    private int currentRetries = 0;
    private Product product = null;
    RestTemplate restTemplate;

    public ValidateProduct(Product product) {
        this.product = product;
        restTemplate = new RestTemplate();
        thread = Thread.currentThread();
    }

    @Override
    public void run() {
        while(true) {
            if (this.currentRetries >=  MAX_RETRIES) {
                this.thread.interrupt();
            }
            validate v = restTemplate.getForObject("http://localhost:9000/product/validate", validate.class);
            if (v != null && v.getIsValid()) {
                return;
            }
            this.currentRetries += 1;
        }
    }
}
