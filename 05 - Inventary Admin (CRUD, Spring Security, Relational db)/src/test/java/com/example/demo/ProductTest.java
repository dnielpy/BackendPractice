package com.example.demo;
import com.example.demo.Product.ProductController;
import com.example.demo.Product.ProductDTO;
import com.example.demo.Product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductTest {

    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateProduct() {
        ProductDTO productDTO = new ProductDTO("Test", 100.0, 10L);
        when(productService.createProduct(anyString(), anyDouble(), anyLong())).thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.createProduct("Test", 100.0, 10);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    public void testGetProduct() {
        ProductDTO productDTO = new ProductDTO("Test", 100.0, 10L);
        when(productService.getProduct(anyString())).thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.getProduct("Test");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    public void testUpdateProduct() {
        ProductDTO productDTO = new ProductDTO("Test", 100.0, 10L);
        when(productService.updateProducts(anyString(), anyString(), anyDouble(), anyLong())).thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.updateProduct("Test", "NewTest", 200.0, 20);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    public void testDeleteProduct() {
        ProductDTO productDTO = new ProductDTO("Test", 100.0, 10L);
        when(productService.deleteProduct(anyString())).thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.deleteProduct("Test");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
    }

    @Test
    public void testUpdateProductStock() {
        ProductDTO productDTO = new ProductDTO("Test", 100.0, 10L);
        when(productService.updateProductsStock(anyString(), anyLong())).thenReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.updateProductStock("Test", 20);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productDTO, response.getBody());
    }
}