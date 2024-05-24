package com.example.demo;
import org.hibernate.boot.model.relational.Database;
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
    @Autowired
    private CartRepository cartRepository;

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

    //El bug esta en que esta editando, no agregando
    @PostMapping("/addToCart")
        public String addToCart(@RequestParam int productID){
            Products product = productsRepository.findById(productID).orElse(null);

            // Crear un nuevo carrito cada vez que se llame a este método
            Cart cart = new Cart(0, product.getName());

            cartRepository.save(cart);

            return "Producto agregado al carrito" + cart.getProducts();
    }

    public String buyProduct(Products product, Clients client) {
        //Verificar si el usuario tiene el dinero para comprar el producto
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

    @PostMapping("/buyProducts")
    public String buyProducts(@RequestParam int userID){
        List<Cart> cart = cartRepository.findAll();
        Clients client = clientsRepository.findById(userID).orElse(null);

        //Recorrer Cart y sacar todos los productos que tengan el id igual al userID
        List<Cart> carts = cartRepository.findAll();
        for (Cart c : carts) {
                String[] products = c.getProducts().split(", ");
                for (String p : products) {
                    //Aqui tengo todos los productos que ese user annadio al cart
                    Products product = productsRepository.findByName(p);

                    buyProduct(product, client);
            }
        }
        cartRepository.deleteAll();
        return "Producto Comprado con exito";
    }
}
