//package com.example.demo;
//
//import com.example.demo.dtos.Cart;
//import com.example.demo.dtos.SaleDTO;
//import com.example.demo.dtos.UserDTO;
//import com.example.demo.entitys.ProductEntity;
//import com.example.demo.entitys.SaleEntity;
//import com.example.demo.entitys.UserEntity;
//import com.example.demo.services.SaleService;
//import com.example.demo.repositories.ProductRepository;
//import com.example.demo.repositories.SaleRepository;
//import com.example.demo.repositories.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class SaleServiceTest {
//
//    @Mock
//    private SaleRepository saleRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @InjectMocks
//    private SaleService saleService;
//
//    @Test
//    public void testCreateSale() {
//        UserDTO userDTO = new UserDTO("test@gmail.com", "password", 0.0);
//        UserEntity userEntity = new UserEntity(userDTO.getEmail(), userDTO.getPassword(), userDTO.getCredit());
//        Cart cart = new Cart();
//        double total = 100.0;
//        String date = "2022-01-01";
//
//        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(userEntity);
//        SaleDTO result = saleService.createSale(userDTO, cart, total, date);
//        assertNotNull(result, "SaleDTO should not be null");
//        assertEquals(userDTO.getEmail(), result.getEmail());
//    }
//
//    @Test
//    public void testGetSale() {
//        UserDTO userDTO = new UserDTO("test@gmail.com", "password", 0.0);
//        UserEntity userEntity = new UserEntity(userDTO.getEmail(), userDTO.getPassword(), userDTO.getCredit());
//        SaleEntity saleEntity = new SaleEntity(userEntity, new ArrayList<>(), 100.0, "2022-01-01");
//
//        when(saleRepository.findByEmail(userDTO.getEmail())).thenReturn(saleEntity);
//        SaleDTO result = saleService.getSale(userDTO);
//        assertNotNull(result, "SaleDTO should not be null");
//        assertEquals(userDTO.getEmail(), result.getEmail());
//    }
//
//    @Test
//    public void testDeleteSale() {
//        UserDTO userDTO = new UserDTO("test@gmail.com", "password", 0.0);
//        UserEntity userEntity = new UserEntity(userDTO.getEmail(), userDTO.getPassword(), userDTO.getCredit());
//        SaleEntity saleEntity = new SaleEntity(userEntity, new ArrayList<>(), 100.0, "2022-01-01");
//
//        when(saleRepository.findByEmail(userDTO.getEmail())).thenReturn(saleEntity);
//        SaleDTO result = saleService.deleteSale(userDTO);
//        assertEquals(null, result);
//    }
//}