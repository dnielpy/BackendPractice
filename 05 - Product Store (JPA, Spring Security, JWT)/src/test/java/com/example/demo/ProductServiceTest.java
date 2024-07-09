package com.example.demo;

import com.example.demo.Product.*;
import com.example.demo.Category.CategoryEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testCreateProduct() {
        // Arrange
        String name = "testproduct";
        when(productRepository.findByName(name)).thenReturn(null);

        // Act
        ProductDTO result = productService.createProduct(name, 10.0, "longDescription", "shortDescription", 100, new byte[0], new CategoryEntity());

        // Assert
        assertNotNull(result);
        assertEquals(name, result.getName());
    }

    @Test
    public void testGetProduct() {
        // Arrange
        String name = "testProduct";
        ProductEntity productEntity = new ProductEntity(name, 10.0, "longDescription", "shortDescription", 100, new byte[0], new CategoryEntity());
        when(productRepository.findByName(name)).thenReturn(productEntity);

        // Act
        ProductDTO result = productService.getProduct(name);

        // Assert
        assertNotNull(result);
        assertEquals(name, result.getName());
    }

    @Test
    public void testUpdateProducts() {
        // Arrange
        String name = "testProduct";
        String newName = "newTestProduct";
        ProductEntity productEntity = new ProductEntity(name, 10.0, "longDescription", "shortDescription", 100, new byte[0], new CategoryEntity());
        when(productRepository.findByName(name)).thenReturn(productEntity);
        when(productRepository.findByName(newName)).thenReturn(null);

        // Act
        ProductDTO result = productService.updateProducts(name, newName, 20.0, "newLongDescription", "newShortDescription", 200, new byte[0], new CategoryEntity());

        // Assert
        assertNotNull(result);
        assertEquals(newName, result.getName());
    }

    @Test
    public void testUpdateProductsStock() {
        // Arrange
        String name = "testProduct";
        ProductEntity productEntity = new ProductEntity(name, 10.0, "longDescription", "shortDescription", 100, new byte[0], new CategoryEntity());
        when(productRepository.findByName(name)).thenReturn(productEntity);

        // Act
        ProductDTO result = productService.updateProductsStock(name, 200);

        // Assert
        assertNotNull(result);
        assertEquals(200, result.getStock());
    }

//    @Test
//    public void testDeleteProduct() {
//        // Arrange
//        String name = "testProduct";
//        ProductEntity productEntity = new ProductEntity(name, 10.0, "longDescription", "shortDescription", 100, new byte[0], new CategoryEntity());
//        when(productRepository.findByName(name)).thenReturn(productEntity);
//
//        // Act
//        ProductDTO result = productService.deleteProduct(name);
//
//        // Assert
//        assertNull(result);
//    }
}