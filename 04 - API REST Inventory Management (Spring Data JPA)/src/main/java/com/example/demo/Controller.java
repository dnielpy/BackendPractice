package com.example.demo;
import org.hibernate.boot.model.relational.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    private ProductReository productsRepository;
    @Autowired
    private ClientsRepository clientsRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private AdminsRepository adminsRepository;
    @Autowired
    private SalesRepository salesRepository;

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

                    Sales sale = new Sales(client.getName(), client.getProducts(), product.getPrice());
                    salesRepository.save(sale);
                    buyProduct(product, client);
            }
        }
        cartRepository.deleteAll();
        return "Producto Comprado con exito";
    }

    @PostMapping("/cancel")
    public String cancelPurchase(){
        cartRepository.deleteAll();
        return "Compra cancelada con exito";
    }

    @GetMapping("/admin")
    public String loginAdmin(@RequestParam int id){
        boolean itsAnAdmin = false;

        //Check if id is an admin id
        List<Admins> admins = adminsRepository.findAll();

        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getId() == (id)) {
                itsAnAdmin = true;
                break;
            }
        }
        if (itsAnAdmin) {
            return "Es un admin";
        }
        else {
            return "No es un admin";
        }
    }

    @PostMapping("/addAdmin")
    public String addAdmin(@RequestParam int id, @RequestParam String name){
        Admins new_admin = new Admins(id, name);
        adminsRepository.save(new_admin);

        return "Admin agregado con exito \n" + "Id: " + id + "\nName: " + name;
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam int id, @RequestParam String name, @RequestParam int price){
        Products new_product = new Products(id, name, price);
        productsRepository.save(new_product);

        return "Producto agregado con exito \n" + "Id: " + id + "\nName: " + name + "\nPrice: " + price;
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@RequestParam int id, @RequestParam String new_name, @RequestParam int new_price){
        Products product = null;
        List<Products> products = productsRepository.findAll();

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                product = products.get(i);
                break;
            }
        }
        product.setName(new_name);
        product.setPrice(new_price);
        productsRepository.save(product);

        return "Producto actualizado con exito \n" + "Id: " + id + "\nName: " + product.getName() + "\nPrice: " + product.getPrice();
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestParam int id){
        List<Products> products = productsRepository.findAll();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                productsRepository.delete(products.get(i));
            }
        }
    return "Producto eliminado con exito";
    }

    @GetMapping("/salesReport")
    public String salesReport(@RequestParam int id){
        boolean is_admin = false;
        //Comprobar que su id sea de un admin
        List<Admins> admins = adminsRepository.findAll();
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getId() == id) {
                is_admin = true;
                break;
            }
        }
        if (is_admin == true) {
            List<Sales> sales = salesRepository.findAll();
            int sales_amount = 0;
            String report = "";

            for (int i = 0; i < sales.size(); i++) {
                sales_amount += sales.get(i).getCost();
                report += "{\nUsuario: " + sales.get(i).getUsername() + "\nProductos: " + sales.get(i).getProducts() + "\nPrecio Total: " + sales.get(i).getCost() + "\n}";
            }
            report += "\n\nDinero Total Recaudado: " + String.valueOf(sales_amount);
            return report;
        }
        else {
            return null;
        }
    }
}
