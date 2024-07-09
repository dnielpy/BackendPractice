package com.example.demo.Product;

import com.example.demo.Cart.CartRepository;
import com.example.demo.Category.CategoryEntity;
import com.example.demo.Order.OrderRepository;
import com.example.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ProductService productService;

    @Autowired
    public ProductService(UserRepository userRepository, CartRepository cartRepository, ProductRepository productRepository, OrderRepository orderRepository, ProductService productService) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public byte[] convertImageToByteArray(String imagePath) {
        try {
            BufferedImage bImage = ImageIO.read(new File(imagePath));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error al convertir la imagen a un array de bytes", e);
        }
    }

    //Create
    public ProductDTO createProduct(String name, double cost, String longDescription, String shortDescription, long stock, byte[] image, CategoryEntity category) {
        ProductEntity new_product = productRepository.findByName(name);
        if (new_product == null) {
            new_product = new ProductEntity(name.toLowerCase(), cost, longDescription, shortDescription, stock, image, category);
            productRepository.save(new_product);
            return new_product.toDto();
        } else {
            throw new IllegalArgumentException("El producto ya existe en la base de datos");
        }
    }

    //Get
    public ProductDTO getProduct(String name) {
        ProductEntity new_product = productRepository.findByName(name);
        if (new_product != null) {
            return new_product.toDto();
        } else {
            throw new IllegalArgumentException("El producto no existe en la base de datos");
        }
    }

    //Update
    public ProductDTO updateProducts(String name, String new_name, double new_cost, String new_longDescription, String new_shortDescription, long new_stock, byte[] new_image, CategoryEntity new_category) {
        ProductEntity product = productRepository.findByName(name);
        if (product != null) {
            if (productRepository.findByName(new_name) != null) {
                throw new IllegalArgumentException("Ya existe un producto con ese nombre en la base de datos");
            } else {
                product.setName(new_name);
                product.setCost(new_cost);
                product.setLongDescription(new_longDescription);
                product.setShortDescription(new_shortDescription);
                product.setStock(new_stock);
                product.setImage(new_image);
                product.setCategory(new_category);
                productRepository.save(product);
                return product.toDto();
            }
        } else {
            throw new IllegalArgumentException("El producto no existe en la base de datos");
        }
    }

    //Update Stock
    public ProductDTO updateProductsStock(String name, long new_stock) {
        ProductEntity product = productRepository.findByName(name);
        if (product != null) {
            product.setStock(new_stock);
            productRepository.save(product);
            return product.toDto();
        } else {
            throw new IllegalArgumentException("El producto no existe en la base de datos");
        }
    }

    //Delete
    public ProductDTO deleteProduct(String name) {
        ProductEntity new_product = productRepository.findByName(name.toLowerCase());
        if (new_product != null) {
            productRepository.deleteById(new_product.getId());
            return null;
            //En el controlador controllar cuando mande null
        } else {
            throw new IllegalArgumentException("El producto no existe en la base de datos");
        }
    }
}