package com.example.bazarApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sale_code;
    private LocalDate sale_date;
    private Double total;

    @OneToMany
    private List<Product> productsList;
    @OneToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private Client oneClient;

    public Sale() {
    }

    public Sale(Long sale_code, LocalDate sale_date, Double total, List<Product> productsList, Client oneClient) {
        this.sale_code = sale_code;
        this.sale_date = sale_date;
        this.total = total;
        this.productsList = productsList;
        this.oneClient = oneClient;
    }
}
