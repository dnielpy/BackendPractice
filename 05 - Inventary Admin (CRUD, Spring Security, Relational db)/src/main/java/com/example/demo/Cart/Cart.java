package com.example.demo.Cart;

import com.example.demo.Product.ProductDTO;
import com.example.demo.Product.ProductRepository;

import java.util.List;

public class Cart {

    private ProductRepository productRepository;
    private List<ProductDTO> products;

    public Cart(List<ProductDTO> products) {
        this.products = products;
    }

    //Getters and Setters
    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public void addToCart(ProductDTO productDTO) {
        if (productRepository.findByName(productDTO.getName()) == null) {
            throw new IllegalArgumentException("El producto que intentas agregar no existe");
        }
        else {
            products.add(productDTO);
        }
    }

    public void removeFromCart(ProductDTO productDTO){
        if (productRepository.findByName(productDTO.getName()) == null) {
            throw new IllegalArgumentException("El producto que intentas eliminar no existe");
        }
        if (! products.contains(productDTO)) {
            throw new IllegalArgumentException("El producto no esta en tu carrito");
        }
        else {
            products.remove(productDTO);
        }
    }

}
