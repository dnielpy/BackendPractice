# NotesApp 📝📚

This project is a backend application developed in Java using the Spring Boot framework. The application allows users to create, read, update, and delete notes and lists.

### Features 🚀

1. **Users**: Users can register, log in, update their information, and delete their account.

2. **Notes**: Users can create, read, update, and delete notes.

3. **Lists**: Users can create, read, update, and delete lists. They can also add notes to the lists.

### Endpoints 🌐

1. **Users**
    - `POST /user`: Creates a new user. The required parameters are `username` and `password`.
    - `GET /user`: Gets a user's information. The required parameter is `username`.
    - `PUT /user`: Updates a user's information. The required parameters are `username`, `new_username`, and `new_password`.
    - `DELETE /user`: Deletes a user. The required parameters are `username` and `password`.

2. **Notes**
    - `POST /notes`: Creates a new note. The required parameters are `username`, `tittle`, and `note`.
    - `GET /notes`: Gets a note. The required parameter is `tittle`.
    - `PUT /notes`: Updates a note. The required parameters are `tittle` and `note`.
    - `DELETE /notes`: Deletes a note. The required parameter is `tittle`.

3. **Lists**
    - `POST /list`: Creates a new list. The required parameters are `username` and `tittle`.
    - `GET /list`: Gets a list. The required parameter is `tittle`.
    - `PUT /list`: Updates a list. The required parameters are `username` and `tittle`.
    - `DELETE /list`: Deletes a list. The required parameter is `tittle`.
    - `PUT /list/addList`: Adds a note to a list. The required parameters are `username`, `list_tittle`, and `note_tittle`.

### How to Run the Project 🏃‍♂️

This project uses Maven, so you can build and run the project using the `mvn spring-boot:run` command at the root of the project.

### Testing 🧪

To test the endpoints, you can use tools like Postman or cURL. Make sure to include the required parameters in each request.

