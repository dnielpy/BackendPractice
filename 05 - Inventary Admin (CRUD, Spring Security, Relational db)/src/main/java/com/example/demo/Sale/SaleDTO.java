package com.example.demo.Sale;

public class SaleDTO {
    private String email;
    private double total;
    private String date;

    public SaleDTO(String email, double total, String date) {
        this.email = email;
        this.total = total;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public double getTotal() {
        return total;
    }

    public String getDate() {
        return date;
    }
}
