## Simple Task Management System

### Description

This application is a Spring Boot application that allows users to create, read, update, and delete tasks.

### Requirements

- Spring Data JPA
- H2 database
- Lombok
- Unit tests

### Steps

1. Create a new Spring Boot project.
2. Add the following dependencies to your pom.xml file:
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
3. Create a Task entity with fields such as id, name, and description.
4. Create a TaskRepository interface that extends JpaRepository.
5. Create a TaskController REST controller with methods to create, read, update, and delete tasks.
6. Write unit tests to verify the application's functionality.
7. Package and run the application.