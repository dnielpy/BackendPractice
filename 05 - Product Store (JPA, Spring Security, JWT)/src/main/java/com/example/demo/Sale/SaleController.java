package com.example.demo.Sale;

import com.example.demo.Cart.CartEntity;
import com.example.demo.User.UserDTO;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    SaleService saleService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<SaleDTO> createSale(@RequestBody UserDTO userDTO, CartEntity cart, double total, String date) {
        SaleDTO result = saleService.createSale(userDTO, cart, total, date);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<SaleDTO> deleteSale(@RequestBody UserDTO userDTO) {
        SaleDTO result = saleService.deleteSale(userDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SaleDTO>> getAllSales() {
        List<SaleDTO> result = saleService.getAllSales();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<SaleDTO>> getSalesByUser(@RequestBody UserDTO userDTO) {
        List<SaleDTO> result = saleService.getSalesByUser(userDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<List<SaleDTO>> getSalesByDate(@RequestParam String date) {
        List<SaleDTO> result = saleService.getSalesByDate(date);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/user-date")
    public ResponseEntity<List<SaleDTO>> getSalesByUserAndDate(@RequestParam String email, @RequestParam String date) {
        UserEntity user = userRepository.findByEmail(email);
        UserDTO userDTO = new UserDTO(user.getEmail(), user.getCredit());
        List<SaleDTO> result = saleService.getSalesByUserAndDate(userDTO, date);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<List<SaleDTO>> getSalesByTotal(@RequestParam double total) {
        List<SaleDTO> result = saleService.getSalesByTotal(total);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/range")
    public ResponseEntity<List<SaleDTO>> getSaleByRange(@RequestParam double min, @RequestParam double max) {
        List<SaleDTO> result = saleService.getSaleByRange(min, max);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}