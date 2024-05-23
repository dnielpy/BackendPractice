package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private ProductReository productsRepository;
    @Autowired
    private ClientsRepository clientsRepository;

    @GetMapping("/viewProducts")
    public List<Products> viewProducts(){
        List<Products> products = productsRepository.findAll();
        return products;
    }

    @PostMapping("/viewProducts")
    public Products getPreoduct(@RequestParam int id){
        Products product = productsRepository.findById(id).orElse(null);
        return product;
    }

    @PostMapping("/buyProducts")
    public String buyProducts(@RequestParam int userID, @RequestParam int productID){
        Products product = productsRepository.findById(productID).orElse(null);
        Clients client = clientsRepository.findById(userID).orElse(null);



        //Verificar si el usuario tiene el dinero para comprar el productto
        if (product.getPrice() <= client.getBalance()) {
            client.setBalance(client.getBalance() - product.getPrice());

            //Eliminar el null y poner el nombre del producto
            if (client.getProducts() == null) {
                client.setProducts(product.getName());
            } else {
                client.setProducts(client.getProducts() + ", " + product.getName());
            }
            clientsRepository.save(client);
        }
        String productos_comprados = "Productos comprados: " + client.getProducts() + "\nSaldo restante: " + client.getBalance();

        return productos_comprados;
    }
}
