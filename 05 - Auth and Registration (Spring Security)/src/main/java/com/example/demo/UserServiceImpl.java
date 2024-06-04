package com.example.demo;

public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Users findByName(String name){
        return userRepository.findByUserName(name);
    }

    @Override
    public Users save(UserDTO userDTO){
        //Logica para guardar un usuario

        return null;
    }

}
