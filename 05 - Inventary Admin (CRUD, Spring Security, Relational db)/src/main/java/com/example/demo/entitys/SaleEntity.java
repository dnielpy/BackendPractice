package com.example.demo.entitys;

import jakarta.persistence.*;

@Entity
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email")
    private String email;
    @Column(name = "total")
    private String total;
    @Column(name = "date")
    private String date;

    public SaleEntity(long id, String email, String total, String date) {
        this.id = id;
        this.email = email;
        this.total = total;
        this.date = date;
    }

    public SaleEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SaleEntity{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", total='" + total + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
