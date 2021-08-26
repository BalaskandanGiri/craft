package com.intuit.craft.services;

import com.intuit.craft.data.ProductData;
import com.intuit.craft.entities.Product;
import com.intuit.craft.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductValidator{

    @Autowired
    ProductRepository productRepository;

    Product productMapper(ProductData productData) {
        return Product.builder().name(productData.getName())
                .description(productData.getDescription())
                .build();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product postProduct(ProductData productData) {
        Product product = this.productMapper(productData);
        return productRepository.save(product);
    }

    public Product getProductById(String id) {
        Product product = productRepository.getById(id);
        if (product == null) {
            throw new RuntimeException("No product with id : " + id);
        }
        return product;
    }

    public void delete(String id) {productRepository.delete(id);}

    public Product update(ProductData productData) {
        Product product = this.productMapper(productData);
        if (product.getId() == null || getProductById(product.getId()) == null) {
            throw new RuntimeException("Bad Request");
        }
        return productRepository.save(product);
    }

    public boolean validate(Product product) {
        return true;
    }
}
