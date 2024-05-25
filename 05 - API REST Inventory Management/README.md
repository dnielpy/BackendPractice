# Project Title

Your project title goes here.

## Description

This project is a Java Spring Boot application that provides a simple e-commerce system. It includes functionalities such as viewing products, adding products to a cart, buying products, cancelling purchases, managing admins, adding and updating products, deleting products, and generating sales reports.

## Technologies Used

- Java
- Spring Boot
- Maven

## Setup and Installation

1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn clean install` to build the project.
4. Run `mvn spring-boot:run` to start the application.

## Usage

Here's a brief description of how to use the API:

1. **View Products**: To view all the products, make a GET request to the `/viewProducts` endpoint. This will return a list of all the products.

2. **Get a Specific Product**: To get a specific product, make a POST request to the `/viewProducts` endpoint with the `id` of the product as a request parameter.

3. **Add to Cart**: To add a product to the cart, make a POST request to the `/addToCart` endpoint with the `productID` as a request parameter.

4. **Buy Products**: To buy the products in the cart, make a POST request to the `/buyProducts` endpoint with the `userID` as a request parameter.

5. **Cancel Purchase**: To cancel a purchase, make a POST request to the `/cancel` endpoint.

6. **Admin Login**: To login as an admin, make a GET request to the `/admin` endpoint with the `id` of the admin as a request parameter.

7. **Add Admin**: To add an admin, make a POST request to the `/addAdmin` endpoint with the `id` and `name` of the new admin as request parameters.

8. **Add Product**: To add a product, make a POST request to the `/addProduct` endpoint with the `id`, `name`, and `price` of the new product as request parameters.

9. **Update Product**: To update a product, make a POST request to the `/updateProduct` endpoint with the `id`, `new_name`, and `new_price` as request parameters.

10. **Delete Product**: To delete a product, make a POST request to the `/deleteProduct` endpoint with the `id` of the product as a request parameter.

11. **Sales Report**: To get a sales report, make a GET request to the `/salesReport` endpoint with the `id` of the admin as a request parameter.

Please note that the actual usage may vary depending on the client you are using to make these requests (like Postman, curl, etc.) and the actual server configuration.

## Contributing

Instructions on how to contribute to your project.

## License

Information about the license.