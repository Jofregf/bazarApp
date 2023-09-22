package com.example.bazarApp.dto;

import com.example.bazarApp.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SaleProductDTO {
    private Long code_sale;
    private List<Product> productList;

    public SaleProductDTO() {
    }

    public SaleProductDTO(Long code_sale, List<Product> productList) {
        this.code_sale = code_sale;
        this.productList = productList;
    }
}
