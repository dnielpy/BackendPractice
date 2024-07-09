package com.example.demo;

import com.example.demo.Cart.CartEntity;
import com.example.demo.Cart.CartRepository;
import com.example.demo.Order.OrderDTO;
import com.example.demo.Order.OrderRepository;
import com.example.demo.Product.ProductEntity;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.ProductService;
import com.example.demo.User.*;
import com.example.demo.Order.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    OrderRepository orderRepository;

    @Mock
    ProductService productService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testCreateUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        userService.createUser("test@test.com", "Test", "User", "Country", "City", "Address", "Tel", "Mobile", "Password");
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testGetUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(new UserEntity());
        UserDTO userDTO = userService.getUser("test@test.com");
        assertEquals(userDTO, new UserEntity().toDto());
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(new UserEntity());
        UserDTO userDTO = userService.updateUser("test@test.com", "new@test.com", "New", "User", "NewCountry", "NewCity", "NewAddress", "NewTel", "NewMobile");
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    public void testDeleteUser() {
        when(userRepository.findByEmail(anyString())).thenReturn(new UserEntity());
        userService.deleteUser("test@test.com");
        verify(userRepository, times(1)).delete(any(UserEntity.class));
    }


//    @Test
//    public void testBuy() {
//        when(userRepository.findByEmail(anyString())).thenReturn(new UserEntity());
//        when(productRepository.findById(anyLong())).thenReturn(Optional.of(new ProductEntity()));
//        when(productService.updateProductsStock(anyString(), anyInt())).thenReturn(new ProductEntity().toDto());
//        UserDTO userDTO = new UserEntity().toDto();
//        CartEntity cart = new CartEntity(new UserEntity(), new ArrayList<>());
//        OrderDTO orderDTO = userService.buy(userDTO, cart);
//        assertEquals(orderDTO, new OrderDTO());
//    }
}