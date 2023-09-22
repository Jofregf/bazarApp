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
                                               @RequestParam(required = false, name = "new_code") Long new_code,
                                               @RequestParam(required = false, name = "name") String new_name,
                                               @RequestParam(required = false, name = "brand") String new_brand,
                                               @RequestParam(required = false, name = "cost") Double new_cost,
                                               @RequestParam(required = false, name = "stock_available") Double new_stock){

        productService.editProduct(product_code, new_code, new_name, new_brand, new_cost, new_stock);
        Product edited_product = this.showProduct(product_code).getBody();
        return new ResponseEntity<>(edited_product, HttpStatus.OK);
    }

    @PutMapping("/productos/editar")
    public ResponseEntity<Product> editProduct(@RequestBody Product product) {
        productService.editProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/productos/revisar_stock")
    public List<Product> getLackStock() {
        return productService.getLackStock();
    }

    @GetMapping("/productos/{product_code}/costo")
    public ResponseEntity<String> getProductCost(@PathVariable Long product_code) {
        Double cost = productService.getProductCost(product_code);
        return new ResponseEntity<>("El costo del producto es " + cost, HttpStatus.OK);
    }

    @GetMapping("/productos/{product_code}/sin_stock")
    public ResponseEntity<String> haveStock(@PathVariable Long product_code) {
        Boolean haveStock = productService.haveStock(product_code);
        return new ResponseEntity<>("El producto con ID " + product_code + " tiene stock? " + haveStock,
                                    HttpStatus.OK);
    }
}
