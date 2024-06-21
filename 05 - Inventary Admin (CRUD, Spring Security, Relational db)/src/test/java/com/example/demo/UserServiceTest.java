package com.example.demo;

import com.example.demo.dtos.UserDTO;
import com.example.demo.entitys.UserEntity;
import com.example.demo.services.UserService;
import com.example.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        String email = "test1@example.com";
        String password = "password";
        UserEntity userEntity = new UserEntity(email.toLowerCase(Locale.ROOT), password, 0.0);

        when(userRepository.findByEmail(email)).thenReturn(null).thenReturn(userEntity);
        UserDTO result = userService.createUser(email, password);
        assertNotNull(result, "UserDTO should not be null");
        assertEquals(email.toLowerCase(Locale.ROOT), result.getEmail());
    }

    @Test
    public void testGetUser() {
        String email = "test@gmail.com";
        UserEntity userEntity = new UserEntity(email, "password", 0.0);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        UserDTO result = userService.getUser(email);
        assertNotNull(result, "UserDTO should not be null");
        assertEquals(email, result.getEmail());
    }

    @Test
    public void testUpdateUser() {
        String email = "test@gmail.com";
        String newEmail = "newtest@gmail.com";
        String newPassword = "newpassword";
        UserEntity userEntity = new UserEntity(email, "password", 0.0);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        when(userRepository.findByEmail(newEmail)).thenReturn(null);
        UserDTO result = userService.updateUser(email, newEmail, newPassword);
        assertNotNull(result, "UserDTO should not be null");
        assertEquals(newEmail, result.getEmail());
    }

    @Test
    public void testUpdateUserCredit() {
        String email = "test@gmail.com";
        double newCredit = 100.0;
        UserEntity userEntity = new UserEntity(email, "password", 0.0);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        UserDTO result = userService.updateUserCredit(email, newCredit);
        assertNotNull(result, "UserDTO should not be null");
        assertEquals(newCredit, result.getCredit());
    }

    @Test
    public void testDeleteUser() {
        String email = "test@gmail.com";
        UserEntity userEntity = new UserEntity(email, "password", 0.0);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        UserDTO result = userService.deleteUser(email);
        assertEquals(null, result);
    }
}