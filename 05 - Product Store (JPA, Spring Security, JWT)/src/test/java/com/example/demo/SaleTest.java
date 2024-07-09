package com.example.demo;

import com.example.demo.User.UserDTO;
import com.example.demo.User.UserRepository;
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

public class SaleTest {

    @InjectMocks
    SaleController saleController;

    @Mock
    SaleService saleService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateSale() {
        UserDTO userDTO = new UserDTO("test@test.com", 100.0);
        SaleDTO saleDTO = new SaleDTO("test@test.com", 100.0, "2022-01-01");
        when(saleService.createSale(any(UserDTO.class), any(), anyDouble(), anyString())).thenReturn(saleDTO);

        ResponseEntity<SaleDTO> response = saleController.createSale(userDTO, null, 100.0, "2022-01-01");

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(saleDTO, response.getBody());
    }

    @Test
    public void testDeleteSale() {
        UserDTO userDTO = new UserDTO("test@test.com", 100.0);
        SaleDTO saleDTO = new SaleDTO("test@test.com", 100.0, "2022-01-01");

        when(saleService.deleteSale(any(UserDTO.class))).thenReturn(saleDTO);

        ResponseEntity<SaleDTO> response = saleController.deleteSale(userDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(saleDTO, response.getBody());
    }

    @Test
    public void testGetAllSales() {
        SaleDTO saleDTO = new SaleDTO("test@test.com", 100.0, "2022-01-01");

        when(saleService.getAllSales()).thenReturn(Collections.singletonList(saleDTO));

        ResponseEntity<List<SaleDTO>> response = saleController.getAllSales();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(saleDTO, response.getBody().get(0));
    }
    @Test
    public void testGetSalesByUser() {
        UserDTO userDTO = new UserDTO("test@test.com", 100.0);
        SaleDTO saleDTO = new SaleDTO("test@test.com", 100.0, "2022-01-01");
        when(saleService.getSalesByUser(any(UserDTO.class))).thenReturn(Collections.singletonList(saleDTO));

        ResponseEntity<List<SaleDTO>> response = saleController.getSalesByUser(userDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(saleDTO, response.getBody().get(0));
    }

    @Test
    public void testGetSalesByDate() {
        SaleDTO saleDTO = new SaleDTO("test@test.com", 100.0, "2022-01-01");
        when(saleService.getSalesByDate(anyString())).thenReturn(Collections.singletonList(saleDTO));

        ResponseEntity<List<SaleDTO>> response = saleController.getSalesByDate("2022-01-01");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(saleDTO, response.getBody().get(0));
    }

//    @Test
//    public void testGetSalesByUserAndDate() {
//        UserDTO userDTO = new UserDTO("test@test.com", 100.0);
//        SaleDTO saleDTO = new SaleDTO("test@test.com", 100.0, "2022-01-01");
//        when(userRepository.findByEmail(anyString())).thenReturn(new UserEntity("test@test.com", 100.0));
//        when(saleService.getSalesByUserAndDate(any(UserDTO.class), anyString())).thenReturn(Collections.singletonList(saleDTO));
//
//        ResponseEntity<List<SaleDTO>> response = saleController.getSalesByUserAndDate("test@test.com", "2022-01-01");
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1, response.getBody().size());
//        assertEquals(saleDTO, response.getBody().get(0));
//    }

    @Test
    public void testGetSalesByTotal() {
        SaleDTO saleDTO = new SaleDTO("test@test.com", 100.0, "2022-01-01");
        when(saleService.getSalesByTotal(anyDouble())).thenReturn(Collections.singletonList(saleDTO));

        ResponseEntity<List<SaleDTO>> response = saleController.getSalesByTotal(100.0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(saleDTO, response.getBody().get(0));
    }

    @Test
    public void testGetSaleByRange() {
        SaleDTO saleDTO = new SaleDTO("test@test.com", 100.0, "2022-01-01");
        when(saleService.getSaleByRange(anyDouble(), anyDouble())).thenReturn(Collections.singletonList(saleDTO));

        ResponseEntity<List<SaleDTO>> response = saleController.getSaleByRange(50.0, 150.0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(saleDTO, response.getBody().get(0));
    }

}