package com.example.bazarApp.service;

import com.example.bazarApp.dto.SaleDayDTO;
import com.example.bazarApp.dto.SaleProductDTO;
import com.example.bazarApp.model.Product;
import com.example.bazarApp.model.Sale;
import com.example.bazarApp.repository.IProductRepository;
import com.example.bazarApp.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SaleService implements ISaleService {

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private IProductService productService;

    @Override
    public Sale addSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public List<Sale> salesList() {
        return saleRepository.findAll();
    }

    @Override
    public Sale getSaleById(Long sale_code) {
        return saleRepository.findById(sale_code)
                .orElseThrow(() -> new NoSuchElementException("No se encuentran las ventas con el Id " + sale_code));
    }

    @Override
    public void deleteSale(Long sale_code) {
        Sale sale = this.getSaleById(sale_code);
        saleRepository.delete(sale);
    }

    @Override
    public Sale editSale(Long sale_code, Long new_code, LocalDate new_date, Double new_total) {
        Sale sale = this.getSaleById(sale_code);
        sale.setSale_code(new_code);
        sale.setSale_date(new_date);
        sale.setTotal(new_total);
        this.addSale(sale);
        return sale;
    }

    @Override
    public Sale editSale(Sale sale) {
        return this.addSale(sale);
    }

    @Override
    public void saveSaleCalculation(Sale sale) {
        Double sum = 0.0;
        Double stock = 0.0;
        Sale newSale = new Sale();
        newSale.setSale_code(sale.getSale_code());
        newSale.setSale_date(sale.getSale_date());
        newSale.setOneClient(sale.getOneClient());

        Long[] product_code;
        product_code = new Long[sale.getProductsList().size()];
        for(int i = 0; i < sale.getProductsList().size(); i++) {
            product_code[i] = sale.getProductsList().get(i).getProduct_code();
        }

        sale.setProductsList(productService.onlyProductWithStock(product_code));
        for(int i = 0; i < sale.getProductsList().size(); i++) {
            sum = sum + sale.getProductsList().get(i).getCost();
        }
        sale.setTotal(sum);
        saleRepository.save(sale);

        for(int i = 0; i < sale.getProductsList().size(); i++) {
            Long id = sale.getProductsList().get(i).getProduct_code();
            productService.discountStock(id);
        }
    }

    @Override
    public SaleProductDTO productSale(Long sale_code) {
        SaleProductDTO saleProductDTO = new SaleProductDTO();
        Sale sale = this.getSaleById(sale_code);
        saleProductDTO.setCode_sale(sale.getSale_code());
        saleProductDTO.setProductList(sale.getProductsList());
        return saleProductDTO;

    }

    @Override
    public SaleDayDTO saleDay(String dateString) {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, formatter);
        SaleDayDTO saleDay = new SaleDayDTO();
        List<Sale> salesList = this.salesList();
        Double sum = 0.0;
        Double oneSale = 0.0;
        Double count = 0.0;
        for(Sale sale:salesList) {
            if(sale.getSale_date().equals(date)) {
                oneSale = sale.getTotal();
                sum = sum + oneSale;
                count = count + 1;
            }
            saleDay.setDate(date);
            saleDay.setAmounSale(count);
            Double roundSum = Math.round(sum * 100.0)/100.0;
            saleDay.setTotalSum(roundSum);
        }
        return saleDay;
    }
}
