package com.example.demo.auth;

public class RegisterRequest {
    private String email;
    private String password;
    private double credit;

    public RegisterRequest(String email, String password, double credit) {
        this.email = email;
        this.password = password;
        this.credit = credit;
    }

    public RegisterRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }
}
