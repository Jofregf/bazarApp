package com.example.bazarApp.service;

import com.example.bazarApp.dto.SaleDayDTO;
import com.example.bazarApp.dto.SaleProductDTO;
import com.example.bazarApp.model.Sale;

import java.time.LocalDate;
import java.util.List;

public interface ISaleService {

    public Sale addSale(Sale sale);

    public List<Sale> salesList();

    public Sale getSaleById(Long sale_code);

    public void deleteSale(Long sale_code);

    public Sale editSale(Long sale_code, Long new_code, LocalDate new_date, Double new_total);

    public Sale editSale(Sale sale);

    public void saveSaleCalculation(Sale sale);

    public SaleProductDTO productSale(Long sale_code);

    public SaleDayDTO saleDay(String date);
    
}
