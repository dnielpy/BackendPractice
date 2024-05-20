# 📦 ElectroShop Inventory Management Application

## 🌐 Overview

Una empresa llamada ElectroShop tiene varias tiendas en fisico, pero este año han decidido dar el salto a las ventas online. La infraestructura de ElectroShop esta compuesta por varias sucursales en diferentes lugares, cada una encargada de vender diferentes tipos de productos. 

Estos productos son:
- Celulares
- Carros
- Comida

ElectroShop tiene interes en controlar sus estadisticas de ventas, por lo que es inmportante para ellos guardar informacion como:
- Nombres de usuario (clientes)
- Productos comprados
- Total gastado en la tienda

Ahora, en cuanto a la funcionalidad de la tienda. esta tiene que contar con una pagina de inicio en la que el usuario pueda loggearse. Solo se puede comprar una vez se conocen sus datos:
- usuario
- correo
- credit card

Luego, el usuario puede elegir entre varios productos y al presionar sobre el boton COMPRAR, toda esta informacion se guardara en la base de datos y se le entregara una factura que refleje los productos comprados, su nombre, correo y total pagado

Tambien tendra un lado ADMIN, su unica funcionalidad es ver los datos de los usuarios hasta la fecha en su tienda (name, email) y ver el total de dinero rcaudado.

Tu mision es esa, construir el backend de una app encargada de autentificar al usuario, gestionar sus conpras y generar una factura. 




## 🚀 Features

1. **Data Models** 📝: The application includes models to represent the products sold by ElectroShop, with attributes such as name, description, price, and quantity in stock. It also includes models to represent customer orders and the customers themselves.

2. **Database** 🗃️: The application uses a database to store all of ElectroShop's information. The database is designed with relationships between the different models in mind. For example, an order is related to a customer and multiple products.

3. **Backend** ⚙️: The backend of the application interacts with the database, providing endpoints for CRUD (Create, Read, Update, Delete) operations for each data model. It also provides endpoints for generating reports and statistics on ElectroShop's inventory performance

4. **API Documentation** 📚: The application's API is documented using tools like Swagger, making it easier for other developers and the ElectroShop team to use.

5. **User Interface** 🖥️: While the main focus is on the backend, the application also includes a simple user interface. This interface, which can be developed using pure JavaScript or a framework like Svelte, Angular, or React, allows the ElectroShop team to interact with the application in an easy and intuitive way.

6. **Testing** ✔️: The application includes tests to ensure it works as expected and can handle ElectroShop's demand.

7. **Deployment** 🚀: The application is designed to be deployed so that ElectroShop can use it. Services like Heroku or AWS can be considered for this purpose.

## 🎬 Getting Started

To get started with this project, clone the repository and install the necessary dependencies. Then, run the application and navigate to the provided local URL in your web browser.

## 📜 License

This project is licensed under the terms of the MIT license.