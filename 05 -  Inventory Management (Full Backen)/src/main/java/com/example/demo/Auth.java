package com.example.demo;

import java.util.List;
import java.util.Locale;

public class Auth {
    private String user;
    private List<Users> users_data;

    public Auth(String user, List<Users> users_data) {
        this.user = user;
        this.users_data = users_data;
    }

    //Implementar la logica para saber si ese usuario esta ne la base de datos
    public boolean CheckUser(){
        for (int i = 0; i < users_data.size(); i++) {
            if (this.users_data.get(i).getEmail().toLowerCase(Locale.ROOT).equals(this.user.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }
}
