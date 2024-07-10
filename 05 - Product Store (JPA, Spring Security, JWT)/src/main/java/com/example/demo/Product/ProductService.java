package com.example.demo.Product;

import com.example.demo.Category.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
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

    //getProductsByGender
    public List<ProductDTO> getProductsByGender(char gender) {
        List<ProductEntity> products_entity = productRepository.findAll();
        List<ProductDTO> products = new ArrayList<>();
        for (ProductEntity productEntity : products_entity) {
            if (productEntity.getCategory().getGender() == gender) {
                products.add(productEntity.toDto());
            }
        }
        return products;
    }

    //getProductByRange
    public List<ProductDTO> getProductByRange(double min, double max) {
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (ProductEntity product : productEntities) {
            if (product.getCost() >= min && product.getCost() <= max) {
                productDTOS.add(product.toDto());
            }
        }
        return productDTOS;
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