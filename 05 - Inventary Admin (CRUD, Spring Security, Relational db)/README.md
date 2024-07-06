  # My Application

## Introduction

This application is a simple product management system built with Java, Spring Boot, and Maven. It allows users to create, read, update, and delete products. Additionally, it provides admin management functionalities.

## Features

### Product Management

- **Create Product**: This feature allows you to create a new product. You need to provide the product name, price, and quantity.

- **Get Product**: This feature allows you to retrieve the details of a specific product by providing the product name.

- **Update Product**: This feature allows you to update the details of a specific product. You need to provide the current product name, new product name, new price, and new quantity.

- **Delete Product**: This feature allows you to delete a specific product by providing the product name.

- **Update Product Stock**: This feature allows you to update the stock of a specific product. You need to provide the product name and the new quantity.

### Admin Management

- **Create Admin**: This feature allows you to create a new admin. You need to provide the admin email and password.

- **Get Admin**: This feature allows you to retrieve the details of a specific admin by providing the admin email.

- **Update Admin**: This feature allows you to update the details of a specific admin. You need to provide the current admin email, new admin email, and new password.

## How to Use

1. **Clone the repository**: First, you need to clone the repository to your local machine.

2. **Build the project**: Navigate to the project directory and run `mvn clean install` to build the project.

3. **Run the application**: Run the application using the command `mvn spring-boot:run`.

4. **Access the endpoints**: Once the application is running, you can access the endpoints using any HTTP client like Postman. The base URL for the application is `http://localhost:8080`.

Please note that this is a basic guide and the actual usage may vary based on the specific requirements of your project.