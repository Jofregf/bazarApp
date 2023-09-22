package com.example.bazarApp.service;

import com.example.bazarApp.model.Product;
import com.example.bazarApp.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Product editProduct(Product product) {
        this.getProduct(product.getProduct_code());
        return this.addProduct(product);
    }

    @Override
    public Product editProduct(Long product_code, Long new_code, String new_name, String new_brand,
                               Double new_cost, Double new_stock) {
        Product product = this.getProduct(product_code);
        product.setProduct_code(new_code);
        product.setName(new_name);
        product.setBrand(new_brand);
        product.setCost(new_cost);
        product.setStock_available(new_stock);

        this.addProduct(product);

        return product;
    }

    @Override
    public List<Product> getLackStock() {
        List<Product> productsList = this.productList();
        List<Product> stocksList = new ArrayList<>();
        for(Product product:productsList) {
            if(product.getStock_available() < 5) {
                stocksList.add(product);
            }
        }
        return stocksList;
    }

    @Override
    public Double getProductCost(Long product_code) {
        Double cost = 0.0;
        List<Product> productsList = this.productList();
        for(Product product:productsList) {
            this.getProduct(product_code);
            if(product.getProduct_code().equals(product_code)) {
                cost = product.getCost();
            }
        }
        return cost;
    }

    @Override
    public Boolean haveStock(Long product_code) {
        List<Product> productsList = this.productList();
        Boolean haveStock =  true;
        for(Product product:productsList) {
            this.getProduct(product_code);
            if(product.getProduct_code().equals(product_code)) {
                if(product.getStock_available() <= 0) {
                    haveStock = false;
                }
            }
        }
        return haveStock;
    }

    @Override
    public void discountStock(Long product_code) {
        List<Product> productsList = this.productList();
        Double stock = 0.0;
        Product produ = new Product();
        for(Product product:productsList) {
            if(product.getProduct_code().equals(product_code)) {
                stock = product.getStock_available() - 1;
                produ.setProduct_code(product.getProduct_code());
                produ.setName(product.getName());
                produ.setBrand(product.getBrand());
                produ.setCost(product.getCost());
                produ.setStock_available(stock);
                produ.setSales(product.getSales());
            }
        }
        this.editProduct(produ);
    }

    @Override
    public List<Product> onlyProductWithStock(Long[] product_code) {
        List<Product> productsList = new ArrayList<>();
        List<Product> updatedList = new ArrayList<>();
        Long code;
        Double stock = 0.0;
        Long idWithStock;
        Boolean dontInclude = false;
        for(int i = 0; i < product_code.length; i++) {
            code = product_code[i];
            productsList.add(this.getProduct(code));
        }
        for(int j = 0; j < productsList.size(); j++) {
            if(productsList.get(j).getStock_available() <= stock) {
                dontInclude = true;
            }
            else {
                idWithStock = productsList.get(j).getProduct_code();
                updatedList.add(this.getProduct(idWithStock));
            }
        }
        return updatedList;
    }


}
