package com.example.bazarApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @ManyToMany(mappedBy = "productsList")
    @JsonIgnore
    private List<Sale> sales;

    public Product() {
    }

    public Product(Long product_code, String name, String brand, Double cost, Double stock_available, List<Sale> sales) {
        this.product_code = product_code;
        this.name = name;
        this.brand = brand;
        this.cost = cost;
        this.stock_available = stock_available;
        this.sales = sales;
    }
}
