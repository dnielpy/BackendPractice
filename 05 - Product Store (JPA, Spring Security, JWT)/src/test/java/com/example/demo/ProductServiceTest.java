package com.example.demo;

import com.example.demo.Category.CategoryEntity;
import com.example.demo.Product.ProductDTO;
import com.example.demo.Product.ProductEntity;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
    public void testGetProductsByGender() {
        // Arrange
        char gender = 'M';
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(new ProductEntity("testProduct1", 10.0, "longDescription1", "shortDescription1", 100, new byte[0], new CategoryEntity("category1", gender)));
        productEntities.add(new ProductEntity("testProduct2", 20.0, "longDescription2", "shortDescription2", 200, new byte[0], new CategoryEntity("category2", gender)));
        when(productRepository.findAll()).thenReturn(productEntities);

        // Act
        List<ProductDTO> result = productService.getProductsByGender(gender);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        for (ProductDTO productDTO : result) {
            assertEquals(gender, productDTO.getCategory().getGender());
        }
    }

    @Test
    public void testGetProductByRange() {
        // Arrange
        double min = 10.0;
        double max = 20.0;
        List<ProductEntity> productEntities = new ArrayList<>();
        productEntities.add(new ProductEntity("testProduct1", 10.0, "longDescription1", "shortDescription1", 100, new byte[0], new CategoryEntity("category1", 'M')));
        productEntities.add(new ProductEntity("testProduct2", 20.0, "longDescription2", "shortDescription2", 200, new byte[0], new CategoryEntity("category2", 'F')));
        when(productRepository.findAll()).thenReturn(productEntities);

        // Act
        List<ProductDTO> result = productService.getProductByRange(min, max);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        for (ProductDTO productDTO : result) {
            assertTrue(productDTO.getCost() >= min && productDTO.getCost() <= max);
        }
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