package com.example.bazarApp.service;

import com.example.bazarApp.model.Product;

import java.util.List;

public interface IProductService {

    public Product addProduct(Product product);

    public List<Product> productList();

    public Product getProduct(Long product_code);

    public void deleteProduct(Long product_code);

    public Product editProduct(Long product_code, Product product);
}
