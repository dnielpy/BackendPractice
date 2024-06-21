package com.example.demo.services;

import com.example.demo.dtos.Cart;
import com.example.demo.dtos.ProductDTO;
import com.example.demo.dtos.SaleDTO;
import com.example.demo.entitys.ProductEntity;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entitys.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private SaleRepository saleRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Create
    public UserDTO createUser(String email, String password) {
        UserEntity new_user = userRepository.findByEmail(email);
        if (new_user == null) {
//            String token = jwtUtil.generateToken(email);
            return new UserDTO(email, 0.00, "token");
        } else {
            throw new IllegalArgumentException("El email ya existe en la base de datos. Seleccione otro");
        }
    }

    //Get
    public UserDTO getUser(String email) {
        UserEntity new_user = userRepository.findByEmail(email);
        if (new_user != null) {
            return new UserDTO(new_user.getEmail(), new_user.getCredit());
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }

    //Update
    public UserDTO updateUser(String email, String new_email, String new_password) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            if (userRepository.findByEmail(new_email) != null) {
                throw new IllegalArgumentException("Ya existe un usuario con ese email en la base de datos");
            } else {
                user.setEmail(new_email);
                user.setPassword(passwordEncoder().encode(new_password));
                user.setCredit(0.00);
                userRepository.save(user);
                return new UserDTO(new_email, 0.00);
            }
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }

    //Update Credit
    public UserDTO updateUserCredit(String email, double new_credit) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            user.setCredit(new_credit); // Corrected line
            userRepository.save(user);
            return new UserDTO(user.getEmail(), user.getCredit());
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }

    //Delete
    public UserDTO deleteUser(String email) {
        UserEntity new_user = userRepository.findByEmail(email);
        if (new_user != null) {
            userRepository.delete(new_user);
            return null;
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }

    //Create Cart
    public Cart createCart() {
        List<ProductDTO> products = new ArrayList<>();
        return new Cart(products);
    }

    //addToCart
    public void addToCart(ProductDTO productDTO, Cart cart) {
        if (productRepository.findByName(productDTO.getName()) == null) {
            throw new IllegalArgumentException("El producto que intentas agregar no existe");
        } else {
            cart.addToCart(productDTO);
        }
    }

    //removeFromCart
    public void removeFromCart(ProductDTO productDTO, Cart cart) {
        if (productRepository.findByName(productDTO.getName()) == null) {
            throw new IllegalArgumentException("El producto que intentas eliminar no existe");
        }
        if (!cart.getProducts().contains(productDTO)) {
            throw new IllegalArgumentException("El producto no esta en tu carrito");
        } else {
            cart.getProducts().remove(productDTO);
        }
    }

    //Buy
    public SaleDTO buy(UserDTO userDTO, Cart cart) {
        UserEntity user = userRepository.findByEmail(userDTO.getEmail());

        //Checkear que tenga dinero para pagar
        double total_price = 0;
        for (int i = 0; i < cart.getProducts().size(); i++) {
            total_price += cart.getProducts().get(i).getPrice();
        }
        if (total_price >= user.getCredit()) {
            throw new IllegalArgumentException("Creditos Insuficientes");
        }

        //Chequear que el carrito no este vacio
        if (cart.getProducts().isEmpty()) {
            throw new IllegalArgumentException("El carrito esta vacio");
        } else {
            //Cobrar
            updateUserCredit(user.getEmail(), user.getCredit() - total_price);

            //Actualizer el Stock -1
            for (int i = 0; i < cart.getProducts().size(); i++) {
                ProductEntity product = productRepository.findByName(cart.getProducts().get(i).getName());
                ProductService productService = new ProductService(productRepository);
                productService.updateProductsStock(product.getName(), product.getStock() - 1);
            }

            //Aqui va agregarlo a las ventas
            SaleService saleService = new SaleService(saleRepository);
            return saleService.createSale(userDTO, cart, total_price, "10/9/2024");
        }
    }
}
