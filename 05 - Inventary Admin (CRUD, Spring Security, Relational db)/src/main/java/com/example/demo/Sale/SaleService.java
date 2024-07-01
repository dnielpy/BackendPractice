package com.example.demo.Sale;

import com.example.demo.Cart.Cart;
import com.example.demo.Product.ProductEntity;
import com.example.demo.Product.ProductRepository;
import com.example.demo.User.UserDTO;
import com.example.demo.User.UserEntity;
import com.example.demo.User.UserRepository;
import com.example.demo.User.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SaleService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    //Create
    public SaleDTO createSale(UserDTO userDTO, Cart cart, double total, String date) {
        SaleEntity new_sale = saleRepository.findByUsername(userDTO.getEmail());

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
        SaleEntity new_sale = saleRepository.findByUsername(userDTO.getEmail());
        if (new_sale != null) {
            return new SaleDTO(new_sale.getUser().getEmail(), new_sale.getTotal(), new_sale.getDate());
        } else {
            throw new IllegalArgumentException("El usuario no tiene compras registradas");
        }
    }

    //Delete
    public SaleDTO deleteSale(UserDTO userDTO) {
        SaleEntity new_sale = saleRepository.findByUsername(userDTO.getEmail());
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
