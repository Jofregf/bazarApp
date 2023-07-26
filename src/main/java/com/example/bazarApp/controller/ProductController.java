package com.example.bazarApp.controller;

import com.example.bazarApp.model.Product;
import com.example.bazarApp.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

    @PostMapping("/productos/crear")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/productos")
    public List<Product> productsList() {
        return productService.productList();
    }

    @GetMapping("/productos/{product_code}")
    public ResponseEntity<Product> showProduct(@PathVariable Long product_code) {
        Product product = productService.getProduct(product_code);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/productos/eliminar/{product_code}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long product_code) {
        productService.deleteProduct(product_code);
        return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.OK);
    }

    @PutMapping("/productos/editar/{product_code}")
    public ResponseEntity<Product> editProduct(@PathVariable Long product_code,
                                               @RequestBody Product product) {

        productService.editProduct(product_code, product);
        Product edited_product = this.showProduct(product_code).getBody();
        return new ResponseEntity<>(edited_product, HttpStatus.OK);
    }
}
