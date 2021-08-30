package com.intuit.craft.controllers;

import com.intuit.craft.data.ProductData;
import com.intuit.craft.data.validate;
import com.intuit.craft.entities.Product;
import com.intuit.craft.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(value = "product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Product postProduct(@Valid @RequestBody ProductData product) {
        return productService.postProduct(product);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable String id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product putProduct(@PathVariable String id, @RequestBody ProductData product) {
        return productService.update(id, product);
    }

    @GetMapping("/validate")
    @ResponseStatus(HttpStatus.OK)
    public validate isValid() {
        return new validate(true);
    }
}
