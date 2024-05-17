# 04 - Students Management (API REST) 📚

This is a REST API project developed with Java, Spring Boot, and Maven.

## Description 📝

This project is a REST API for managing students. It allows you to create, read, update, and delete students from a database.

## Technologies Used 💻

- Java
- Spring Boot
- Maven

## Installation 🛠️

To install and run this project locally, you'll need to have Java and Maven installed.

1. Clone the repository to your local machine using `git clone https://github.com/dnielpy/project.git`
2. Navigate to the project directory using `cd project`
3. Compile the project using `mvn clean install`
4. Run the project using `mvn spring-boot:run`

## Usage 🚀

To interact with the API, you can use tools like Postman.

- To get all students: `GET http://localhost:8080/students`
- To get a student by ID: `GET http://localhost:8080/students/{id}`
- To create a new student: `POST http://localhost:8080/students` with the JSON request body for the new student
- To update a student: `PUT http://localhost:8080/students/{id}` with the JSON request body for the updated student
- To delete a student: `DELETE http://localhost:8080/students/{id}`

## Contribution 🤝

Contributions are welcome. For any major changes, please open an issue first to discuss what you would like to change.

## License 📄

[MIT](https://choosealicense.com/licenses/mit/)