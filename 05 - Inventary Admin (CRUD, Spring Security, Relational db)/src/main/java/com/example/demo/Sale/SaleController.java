package com.example.demo.Sale;

import com.example.demo.Admin.AdminDTO;
import com.example.demo.Admin.AdminRepository;
import com.example.demo.Admin.AdminService;
import com.example.demo.User.UserDTO;
import com.example.demo.User.UserRepository;
import com.example.demo.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    AdminRepository adminRepository;

    @GetMapping()
    public ResponseEntity<SaleDTO> getSale(@RequestParam String email) {
        UserService userService = new UserService(userRepository);
        SaleService saleService = new SaleService(saleRepository);
        try {
            UserDTO userDTO = userService.getUser(email);
            SaleDTO saleDTO = saleService.getsale(userDTO);
            return new ResponseEntity<>(saleDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<AdminDTO> deleteAdmin(@RequestParam String email, @RequestParam String password) {
        AdminService adminService = new AdminService(adminRepository);
        try {
            AdminDTO adminDTO = adminService.deleteadmin(email);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
