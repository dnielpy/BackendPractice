package com.example.demo;

import com.example.demo.Cart.CartEntity;
import com.example.demo.Product.ProductEntity;
import com.example.demo.Sale.SaleDTO;
import com.example.demo.User.UserDTO;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import com.example.demo.User.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

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
        assertNull(result);
    }

    @Test
    public void testCreateUserAlreadyExists() {
        String email = "test1@example.com";
        String password = "password";
        UserEntity userEntity = new UserEntity(email.toLowerCase(Locale.ROOT), password, 0.0);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        assertThrows(IllegalArgumentException.class, () -> userService.createUser(email, password));
    }

    @Test
    public void testGetUserNotFound() {
        String email = "test@gmail.com";

        when(userRepository.findByEmail(email)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> userService.getUser(email));
    }

    @Test
    public void testUpdateUserNotFound() {
        String email = "test@gmail.com";
        String newEmail = "newtest@gmail.com";
        String newPassword = "newpassword";

        when(userRepository.findByEmail(email)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(email, newEmail, newPassword));
    }

    @Test
    public void testUpdateUserEmailAlreadyExists() {
        String email = "test@gmail.com";
        String newEmail = "newtest@gmail.com";
        String newPassword = "newpassword";
        UserEntity userEntity = new UserEntity(email, "password", 0.0);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        when(userRepository.findByEmail(newEmail)).thenReturn(userEntity);
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(email, newEmail, newPassword));
    }

    @Test
    public void testUpdateUserCreditNotFound() {
        String email = "test@gmail.com";
        double newCredit = 100.0;

        when(userRepository.findByEmail(email)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> userService.updateUserCredit(email, newCredit));
    }

    @Test
    public void testDeleteUserNotFound() {
        String email = "test@gmail.com";

        when(userRepository.findByEmail(email)).thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(email));
    }
}