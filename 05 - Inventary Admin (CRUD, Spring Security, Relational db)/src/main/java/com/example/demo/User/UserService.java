package com.example.demo.User;

import com.example.demo.Cart.CartEntity;
import com.example.demo.Product.ProductEntity;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.ProductService;
import com.example.demo.Sale.SaleDTO;
import com.example.demo.Sale.SaleRepository;
import com.example.demo.Sale.SaleService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;
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
            new_user = new UserEntity(email, passwordEncoder().encode(password), 0.00);
            userRepository.save(new_user);
            return new UserDTO(email, 0.00);
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

    //Buy
    public SaleDTO buy(UserDTO userDTO, CartEntity cart) {
        UserEntity user = userRepository.findByEmail(userDTO.getEmail());

        List<Optional<ProductEntity>> products = new ArrayList<>();

        for (int i = 0; i < cart.getProducts().size(); i++) {
            products.add(productRepository.findById(cart.getProducts().get(i)));
        }

        //Checkear que tenga dinero para pagar
        double total_price = 0;
        for (Optional<ProductEntity> productEntity : products) {
            total_price += productEntity.get().getPrice();
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
                Optional<ProductEntity> optionalProduct = productRepository.findById(cart.getProducts().get(i));
                if (optionalProduct.isPresent()) {
                    ProductEntity product = optionalProduct.get();
                    ProductService productService = new ProductService(productRepository);
                    productService.updateProductsStock(product.getName(), product.getStock() - 1);
                } else {
                    throw new IllegalArgumentException("El producto no existe en la base de datos");
                }
            }

            //Aqui va agregarlo a las ventas
            SaleService saleService = new SaleService(saleRepository, userRepository, productRepository);
            return saleService.createSale(userDTO, cart, total_price, LocalDate.now().toString());
        }
    }
}
