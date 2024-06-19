package com.example.demo;

import com.example.demo.services.UserService;
import com.example.demo.entitys.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        String email = "test@example.com";
        String password = "password";
        UserEntity userEntity = new UserEntity(email, password, 0.0);

        when(userRepository.findByUserName(email)).thenReturn(null);
        String result = userService.createUser(email, password);
        assertEquals("Usuario creado con exito", result);

        when(userRepository.findByUserName(email)).thenReturn(userEntity);
        result = userService.createUser(email, password);
        assertEquals("El email del usuario ya existe. Por favor seleccione otro", result);
    }
}