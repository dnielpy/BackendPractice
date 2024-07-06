package com.example.demo;

import com.example.demo.User.UserController;
import com.example.demo.User.UserDTO;
import com.example.demo.User.UserService;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import com.example.demo.Sale.SaleDTO;
import com.example.demo.Sale.SaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    SaleService saleService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {
        UserDTO userDTO = new UserDTO("test@test.com", 100.0);
        when(userService.createUser(anyString(), anyString())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.createUser("test@test.com", "password");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    public void testGetUser() {
        UserDTO userDTO = new UserDTO("test@test.com", 100.0);
        when(userService.getUser(anyString())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.getUser("test@test.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    public void testUpdateUser() {
        UserDTO userDTO = new UserDTO("new@test.com", 100.0);
        when(userService.updateUser(anyString(), anyString(), anyString())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.updateUser("test@test.com", "new@test.com", "new_password");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }

    @Test
    public void testUpdateUserCredit() {
        UserDTO userDTO = new UserDTO("test@test.com", 200.0);
        when(userService.updateUserCredit(anyString(), anyDouble())).thenReturn(userDTO);

        ResponseEntity<UserDTO> response = userController.updateUserCredit("test@test.com", 200.0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDTO, response.getBody());
    }
}