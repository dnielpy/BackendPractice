package com.example.demo.Sale;

import com.example.demo.User.UserEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Sale")
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private UserEntity user;
    @ElementCollection
    private List<Long> products;
    @Column(name = "total")
    private Double total;
    @Column(name = "date")
    private String date;

    public SaleEntity(UserEntity user, List<Long> products, Double total, String  date) {
        this.user = user;
        this.products = products;
        this.total = total;
        this.date = date;
    }

    public SaleEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
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
                ", user=" + user +
                ", products=" + products +
                ", total=" + total +
                ", date=" + date +
                '}';
    }
}
