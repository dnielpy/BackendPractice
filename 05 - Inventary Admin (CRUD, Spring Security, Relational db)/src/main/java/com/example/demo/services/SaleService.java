package com.example.demo.services;

import com.example.demo.dtos.Cart;
import com.example.demo.dtos.SaleDTO;
import com.example.demo.dtos.UserDTO;
import com.example.demo.entitys.ProductEntity;
import com.example.demo.entitys.SaleEntity;
import com.example.demo.entitys.UserEntity;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.SaleRepository;
import com.example.demo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SaleService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private SaleRepository saleRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    //Create
    public SaleDTO createSale(UserDTO userDTO, Cart cart, double total, String date) {
        SaleEntity new_sale = saleRepository.findByEmail(userDTO.getEmail());

        UserEntity user = userRepository.findByEmail(userDTO.getEmail());

        List<Long> products_ids = new ArrayList<>();

        for (int i = 0; i < cart.getProducts().size(); i++) {
            ProductEntity product = productRepository.findByName(cart.getProducts().get(i).getName());
            products_ids.add(product.getId());
        }

        new_sale = new SaleEntity(user, products_ids, total, date);
        saleRepository.save(new_sale);
        return new SaleDTO(new_sale.getUser().getEmail(), total, date);
    }

    //Get
    public SaleDTO getsale(UserDTO userDTO) {
        SaleEntity new_sale = saleRepository.findByEmail(userDTO.getEmail());
        if (new_sale != null) {
            return new SaleDTO(new_sale.getUser().getEmail(), new_sale.getTotal(), new_sale.getDate());
        } else {
            throw new IllegalArgumentException("El usuario no tiene compras registradas");
        }
    }

    //Delete
    public SaleDTO deleteSale(UserDTO userDTO) {
        SaleEntity new_sale = saleRepository.findByEmail(userDTO.getEmail());
        if (new_sale != null) {
            saleRepository.deleteById(new_sale.getId());
            return null;
        } else {
            throw new IllegalArgumentException("El usuario no tiene compras registradas");
        }
    }
    //getPurchases
    public List<SaleDTO> getPurchases() {
        List<SaleEntity> sales = new ArrayList<>();
        sales = saleRepository.findAll();
        List<SaleDTO> saleDTOS = new ArrayList<>();

        for (SaleEntity sale : sales) {
            SaleDTO saleDTO_temp = new SaleDTO(sale.getUser().getEmail(), sale.getTotal(), sale.getDate());
            saleDTOS.add(saleDTO_temp);
        }
        return saleDTOS;
    }
}
