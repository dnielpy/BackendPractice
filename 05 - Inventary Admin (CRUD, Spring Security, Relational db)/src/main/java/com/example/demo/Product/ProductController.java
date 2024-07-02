package com.example.demo.Product;

import com.example.demo.Sale.SaleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestParam String name, @RequestParam double price, long stock) {
        try {
            ProductDTO productDTO = productService.createProduct(name, price, stock);
            return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String name) {
        try {
            ProductDTO productDTO = productService.getProduct(name);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String name, @RequestParam String new_name, @RequestParam double new_price, @RequestParam long new_stock) {
        try {
            ProductDTO productDTO = productService.updateProducts(name, new_name, new_price, new_stock);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<ProductDTO> deleteProduct(@RequestParam String name) {
        try {
            ProductDTO productDTO = productService.deleteProduct(name);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/updateStock")
    public ResponseEntity<ProductDTO> updateProductStock(@RequestParam String name, @RequestParam long new_stock) {
        try {
            ProductDTO productDTO = productService.updateProductsStock(name, new_stock);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
