package com.example.demo.entitys;

import jakarta.persistence.*;

import java.util.Date;
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
    private List<Integer> products;

    @Column(name = "total")
    private Double total;
    @Column(name = "date")
    private Date date;

    public SaleEntity(Long id, UserEntity user, List<Integer> products, Double total, Date date) {
        this.id = id;
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

    public List<Integer> getProducts() {
        return products;
    }

    public void setProducts(List<Integer> products) {
        this.products = products;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
