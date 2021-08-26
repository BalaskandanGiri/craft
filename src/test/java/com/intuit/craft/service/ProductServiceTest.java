package com.intuit.craft.service;

import com.intuit.craft.entities.Product;
import com.intuit.craft.repositories.ProductRepository;
import com.intuit.craft.services.ProductService;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService service = new ProductService();

    @Mock
    ProductRepository productRepository;

    @Test
    @DisplayName("get Product by id")
    void getProductById() {
        when(productRepository.getById(Mockito.any())).thenReturn(TestUtils.getProduct());
        Product product = service.getProductById("123");
        assertNotNull(product);
        assertEquals(product.getId(), "123");
    }

    @Test
    @DisplayName("get all Products")
    void getProducts() {
        when(productRepository.findAll()).thenReturn(TestUtils.getProducts());
        List<Product> product = service.getAllProducts();
        assertNotNull(product);
        assertEquals(product.size(), 2);
    }

    @Test
    @DisplayName("create Product")
    void createProduct() {
        when(productRepository.save(Mockito.any())).thenReturn(TestUtils.getProduct());
        Product product = service.postProduct(TestUtils.getProductData());
        assertNotNull(product);
        assertEquals(product.getId(), "123");
    }

    @Test
    @DisplayName("update Product")
    void updateProduct() {
        when(productRepository.save(Mockito.any())).thenReturn(TestUtils.getProduct());
        when(productRepository.getById(Mockito.any())).thenReturn(TestUtils.getProduct());
        Product product = service.update("123",TestUtils.getProductData());
        assertNotNull(product);
        assertEquals(product.getId(), "123");
    }


}
