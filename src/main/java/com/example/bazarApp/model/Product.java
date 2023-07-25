package com.example.bazarApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long product_code;
    private String name;
    private String brand;
    private Double cost;
    private Double stock_available;

    public Product() {
    }

    public Product(Long product_code, String name, String brand, Double cost, Double stock_available) {
        this.product_code = product_code;
        this.name = name;
        this.brand = brand;
        this.cost = cost;
        this.stock_available = stock_available;
    }
}
