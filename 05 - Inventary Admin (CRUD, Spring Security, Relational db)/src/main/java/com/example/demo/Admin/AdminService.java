package com.example.demo.Admin;

import com.example.demo.Product.ProductRepository;
import com.example.demo.Sale.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleRepository saleRepository;

    public AdminService(AdminRepository adminRepository) {
    }

    @Bean
    public PasswordEncoder adminpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Create
    public AdminDTO createAdmin(String email, String password) {
        AdminEntity new_admin = adminRepository.findByEmail(email);
        if (new_admin == null) {
            new_admin = new AdminEntity(email, adminpasswordEncoder().encode(password));
            adminRepository.save(new_admin);
            return new AdminDTO(email);
        } else {
            throw new IllegalArgumentException("El email ya existe en la base de datos. Seleccione otro");
        }
    }

    //Get
    public AdminDTO getadmin(String email) {
        AdminEntity new_admin = adminRepository.findByEmail(email);
        if (new_admin != null) {
            return new AdminDTO(new_admin.getEmail());
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }

    //Update
    public AdminDTO updateadmin(String email, String new_email, String new_password) {
        AdminEntity admin = adminRepository.findByEmail(email);
        if (admin != null) {
            if (adminRepository.findByEmail(new_email) != null) {
                throw new IllegalArgumentException("Ya existe un usuario con ese email en la base de datos");
            } else {
                admin.setEmail(new_email);
                admin.setPassword(adminpasswordEncoder().encode(new_password));
                adminRepository.save(admin);
                return new AdminDTO(new_email);
            }
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }

    //Delete
    public AdminDTO deleteadmin(String email) {
        AdminEntity new_admin = adminRepository.findByEmail(email);
        if (new_admin != null) {
            adminRepository.delete(new_admin);
            return null;
        } else {
            throw new IllegalArgumentException("El usuario no existe en la base de datos");
        }
    }
}
