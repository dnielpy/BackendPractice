package com.example.demo.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @GetMapping
    public ResponseEntity<AdminDTO> getAdmin(@RequestParam String email) {
        AdminService adminService = new AdminService(adminRepository);
        try {
            AdminDTO adminDTO = adminService.getadmin(email);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AdminDTO> createAdmin(@RequestParam String email, @RequestParam String password) {
        AdminService adminService = new AdminService(adminRepository);
        try {
            AdminDTO adminDTO = adminService.createAdmin(email, password);
            return new ResponseEntity<>(adminDTO, HttpStatus.OK);
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
