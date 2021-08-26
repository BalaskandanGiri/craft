package com.intuit.craft.controllers;

import com.intuit.craft.entities.Product;
import com.intuit.craft.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping()
    public Product postProduct(@RequestBody Product product) {
        return productService.postProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable String id) {
        productService.delete(id);
    }

    @PutMapping()
    public Product putProduct(@RequestBody Product product) {
        return productService.update(product);
    }

}
