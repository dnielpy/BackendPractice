package com.example.demo.Cart;

import com.example.demo.Product.ProductDTO;
import com.example.demo.Product.ProductEntity;
import com.example.demo.Product.ProductRepository;
import com.example.demo.User.UserDTO;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;

import java.util.List;

public class CartService {

    CartRepository cartRepository;
    UserRepository userRepository;
    ProductRepository productRepository;

    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    //Create
    public CartEntity createCart(UserDTO userDTO) {
        CartEntity cartEntity = cartRepository.findByEmail(userDTO.getEmail());
        UserEntity userEntity = userRepository.findByEmail(userDTO.getEmail());
        if (cartEntity == null) {
            cartEntity = new CartEntity(userEntity);
            return cartEntity;
        } else {
            throw new IllegalArgumentException("Ya tienes un carrito creado");
        }
    }

    //addToCart
    public CartEntity addToCart(UserDTO userDTO, ProductDTO productDTO) {
        CartEntity cart = cartRepository.findByEmail(userDTO.getEmail());

        ProductEntity product = productRepository.findByName(productDTO.getName());
        UserEntity user = userRepository.findByEmail(userDTO.getEmail());

        if (product == null) {
            throw new IllegalArgumentException("El producto que intentas agregar no existe");
        }
        if (user == null) {
            throw new IllegalArgumentException("El usuario no existe");
        }
        if (cart == null) {
            cart = new CartEntity(user);
            List<Long> products = cart.getProducts();
            products.add(product.getId());
            cart.setProducts(products);
        } else {
            List<Long> products = cart.getProducts();
            products.add(product.getId());
            cart.setProducts(products);
        }
        cartRepository.save(cart);
        return cart;
    }

    //removeFromCart
    public void removeFromCart(ProductDTO productDTO, CartEntity cart) {
        ProductEntity product = productRepository.findByName(productDTO.getName());
        List<Long> products = cart.getProducts();
        products.remove(product.getId());
        cart.setProducts(products);
        cartRepository.save(cart);
    }
}
