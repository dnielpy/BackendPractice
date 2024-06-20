package com.example.demo;

import com.example.demo.dtos.ProductDTO;
import com.example.demo.entitys.ProductEntity;
import com.example.demo.services.ProductService;
import com.example.demo.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testCreateProduct() {
        String name = "product1";
        double price = 100.0;
        long stock = 10;
        ProductEntity productEntity = new ProductEntity(name, price, stock);

        when(productRepository.findByName(name)).thenReturn(null).thenReturn(productEntity);
        ProductDTO result = productService.createProduct(name, price, stock);
        assertNotNull(result, "ProductDTO should not be null");
        assertEquals(name, result.getName());
    }

    @Test
    public void testGetProduct() {
        String name = "product1";
        ProductEntity productEntity = new ProductEntity(name, 100.0, 10);

        when(productRepository.findByName(name)).thenReturn(productEntity);
        ProductDTO result = productService.getProduct(name);
        assertNotNull(result, "ProductDTO should not be null");
        assertEquals(name, result.getName());
    }

    @Test
    public void testUpdateProducts() {
        String name = "product1";
        String newName = "newproduct1";
        double newPrice = 200.0;
        long newStock = 20;
        ProductEntity productEntity = new ProductEntity(name, 100.0, 10);

        when(productRepository.findByName(name)).thenReturn(productEntity);
        when(productRepository.findByName(newName)).thenReturn(null);
        ProductDTO result = productService.updateProducts(name, newName, newPrice, newStock);
        assertNotNull(result, "ProductDTO should not be null");
        assertEquals(newName, result.getName());
    }

    @Test
    public void testUpdateProductsStock() {
        String name = "product1";
        long newStock = 20;
        ProductEntity productEntity = new ProductEntity(name, 100.0, 10);

        when(productRepository.findByName(name)).thenReturn(productEntity);
        ProductDTO result = productService.updateProductsStock(name, newStock);
        assertNotNull(result, "ProductDTO should not be null");
        assertEquals(newStock, result.getStock());
    }

    @Test
    public void testDeleteProduct() {
        String name = "product1";
        ProductEntity productEntity = new ProductEntity(name, 100.0, 10);

        when(productRepository.findByName(name)).thenReturn(productEntity);
        ProductDTO result = productService.deleteProduct(name);
        assertEquals(null, result);
    }
}