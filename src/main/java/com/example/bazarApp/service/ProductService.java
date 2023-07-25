package com.example.bazarApp.service;

import com.example.bazarApp.model.Product;
import com.example.bazarApp.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    @Override
    public List<Product> productList() {
        List<Product> productsList = productRepository.findAll();
        return productsList;
    }

    @Override
    public Product getProduct(Long product_code) {
//        Producto productoById = productoRepository.findById(codigo_producto).orElse(null);
        Product productById = productRepository.findById(product_code)
                .orElseThrow(() -> new NoSuchElementException("No se encontró ningún producto con el código: " + product_code));
        return productById;
    }

    @Override
    public void deleteProduct(Long product_code) {
        Product product = this.getProduct(product_code);
        productRepository.delete(product);
    }

    @Override
    public Product editProduct(Long product_code, Product product) {
        Product productToEdit = this.getProduct(product_code);
        productToEdit.setName(product.getName());
        productToEdit.setBrand(product.getBrand());
        productToEdit.setCost(product.getCost());
        productToEdit.setStock_available(product.getStock_available());
        productToEdit.setProduct_code(product_code);
        this.addProduct(productToEdit);
        System.out.println(productToEdit.getProduct_code());
        return productToEdit;
    }
}
