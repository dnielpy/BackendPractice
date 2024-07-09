//package com.example.demo;
//
//import com.example.demo.Order.OrderController;
//import com.example.demo.Order.OrderDTO;
//import com.example.demo.Order.OrderService;
//import com.example.demo.User.UserDTO;
//import com.example.demo.User.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class OrderTest {
//
//    @InjectMocks
//    OrderController OrderController;
//
//    @Mock
//    OrderService OrderService;
//
//    @Mock
//    UserRepository userRepository;
//
//    @BeforeEach
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void testCreateOrder() {
//        UserDTO userDTO = new UserDTO("test@test.com", 100.0);
//        OrderDTO OrderDTO = new OrderDTO("test@test.com", 100.0, "2022-01-01");
//        when(OrderService.createOrder(any(UserDTO.class), any(), anyDouble(), anyString())).thenReturn(OrderDTO);
//
//        ResponseEntity<OrderDTO> response = OrderController.createOrder(userDTO, null, 100.0, "2022-01-01");
//
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(OrderDTO, response.getBody());
//    }
//
//    @Test
//    public void testDeleteOrder() {
//        UserDTO userDTO = new UserDTO("test@test.com", 100.0);
//        OrderDTO OrderDTO = new OrderDTO("test@test.com", 100.0, "2022-01-01");
//
//        when(OrderService.deleteOrder(any(UserDTO.class))).thenReturn(OrderDTO);
//
//        ResponseEntity<OrderDTO> response = OrderController.deleteOrder(userDTO);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(OrderDTO, response.getBody());
//    }
//
//    @Test
//    public void testGetAllOrders() {
//        OrderDTO OrderDTO = new OrderDTO("test@test.com", 100.0, "2022-01-01");
//
//        when(OrderService.getAllOrders()).thenReturn(Collections.singletonList(OrderDTO));
//
//        ResponseEntity<List<OrderDTO>> response = OrderController.getAllOrders();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1, response.getBody().size());
//        assertEquals(OrderDTO, response.getBody().get(0));
//    }
//    @Test
//    public void testGetOrdersByUser() {
//        UserDTO userDTO = new UserDTO("test@test.com", 100.0);
//        OrderDTO OrderDTO = new OrderDTO("test@test.com", 100.0, "2022-01-01");
//        when(OrderService.getOrdersByUser(any(UserDTO.class))).thenReturn(Collections.singletonList(OrderDTO));
//
//        ResponseEntity<List<OrderDTO>> response = OrderController.getOrdersByUser(userDTO);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1, response.getBody().size());
//        assertEquals(OrderDTO, response.getBody().get(0));
//    }
//
//    @Test
//    public void testGetOrdersByDate() {
//        OrderDTO OrderDTO = new OrderDTO("test@test.com", 100.0, "2022-01-01");
//        when(OrderService.getOrdersByDate(anyString())).thenReturn(Collections.singletonList(OrderDTO));
//
//        ResponseEntity<List<OrderDTO>> response = OrderController.getOrdersByDate("2022-01-01");
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1, response.getBody().size());
//        assertEquals(OrderDTO, response.getBody().get(0));
//    }
//
////    @Test
////    public void testGetOrdersByUserAndDate() {
////        UserDTO userDTO = new UserDTO("test@test.com", 100.0);
////        OrderDTO OrderDTO = new OrderDTO("test@test.com", 100.0, "2022-01-01");
////        when(userRepository.findByEmail(anyString())).thenReturn(new UserEntity("test@test.com", 100.0));
////        when(OrderService.getOrdersByUserAndDate(any(UserDTO.class), anyString())).thenReturn(Collections.singletonList(OrderDTO));
////
////        ResponseEntity<List<OrderDTO>> response = OrderController.getOrdersByUserAndDate("test@test.com", "2022-01-01");
////
////        assertEquals(HttpStatus.OK, response.getStatusCode());
////        assertEquals(1, response.getBody().size());
////        assertEquals(OrderDTO, response.getBody().get(0));
////    }
//
//    @Test
//    public void testGetOrdersByTotal() {
//        OrderDTO OrderDTO = new OrderDTO("test@test.com", 100.0, "2022-01-01");
//        when(OrderService.getOrdersByTotal(anyDouble())).thenReturn(Collections.singletonList(OrderDTO));
//
//        ResponseEntity<List<OrderDTO>> response = OrderController.getOrdersByTotal(100.0);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1, response.getBody().size());
//        assertEquals(OrderDTO, response.getBody().get(0));
//    }
//
//    @Test
//    public void testGetOrderByRange() {
//        OrderDTO OrderDTO = new OrderDTO("test@test.com", 100.0, "2022-01-01");
//        when(OrderService.getOrderByRange(anyDouble(), anyDouble())).thenReturn(Collections.singletonList(OrderDTO));
//
//        ResponseEntity<List<OrderDTO>> response = OrderController.getOrderByRange(50.0, 150.0);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1, response.getBody().size());
//        assertEquals(OrderDTO, response.getBody().get(0));
//    }
//
//}