package com.example.demo.Sale;

import com.example.demo.Cart.CartEntity;
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
import java.util.Optional;

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
    public SaleDTO createSale(UserDTO userDTO, CartEntity cart, double total, String date) {
        SaleEntity new_sale = saleRepository.findByUsername(userDTO.getEmail());

        UserEntity user = userRepository.findByEmail(userDTO.getEmail());

        List<Optional<ProductEntity>> products = new ArrayList<>();

        for (int i = 0; i < cart.getProducts().size(); i++) {
            products.add(productRepository.findById(cart.getProducts().get(i)));
        }

        List<Long> productId = new ArrayList<>();

        for (Optional<ProductEntity> optionalProduct : products) {
            if (optionalProduct.isPresent()) {
                productId.add(optionalProduct.get().getId());
            } else {
                throw new IllegalArgumentException("El producto no existe en la base de datos");
            }
        }

        new_sale = new SaleEntity(user, productId, total, date);
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
