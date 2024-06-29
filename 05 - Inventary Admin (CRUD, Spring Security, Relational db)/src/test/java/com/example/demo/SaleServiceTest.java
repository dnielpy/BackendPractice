package com.example.demo.services;

import com.example.demo.dtos.Cart;
import com.example.demo.dtos.SaleDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entitys.SaleEntity;
import com.example.demo.entitys.UserEntity;
import com.example.demo.repositories.SaleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SaleServiceTest {

    @InjectMocks
    SaleService saleService;

    @Mock
    SaleRepository saleRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSale() {
        UserDTO userDTO = new UserDTO("test@test.com", 100.0);
        Cart cart = new Cart(new ArrayList<>());
        when(userRepository.findByEmail(anyString())).thenReturn(new UserEntity());
        when(saleRepository.save(any())).thenReturn(new SaleEntity());

        SaleDTO result = saleService.createSale(userDTO, cart, 100.0, "10/9/2024");

        assertNotNull(result);
        verify(saleRepository, times(1)).save(any());
    }

    @Test
    public void testGetSale() {
        UserDTO userDTO = new UserDTO("test@test.com", 100.0);
        when(saleRepository.findByUsername(anyString())).thenReturn(new SaleEntity());

        SaleDTO result = saleService.getsale(userDTO);

        assertNotNull(result);
        verify(saleRepository, times(1)).findByUsername(anyString());
    }

    @Test
    public void testDeleteSale() {
        UserDTO userDTO = new UserDTO("test@test.com", 100.0);
        when(saleRepository.findByUsername(anyString())).thenReturn(new SaleEntity());

        SaleDTO result = saleService.deleteSale(userDTO);

        assertNull(result);
        verify(saleRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testgetPurcgases() {
        List<SaleDTO> result = saleService.getPurchases();
        boolean check = false;
        if (result.size() == 0) {
            check = true;
        }
        assert check;
    }
}

