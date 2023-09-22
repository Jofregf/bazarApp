package com.example.bazarApp.controller;

import com.example.bazarApp.dto.SaleDayDTO;
import com.example.bazarApp.dto.SaleProductDTO;
import com.example.bazarApp.model.Sale;
import com.example.bazarApp.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @PostMapping("/ventas/crear")
    public ResponseEntity<Sale> addSale(@RequestBody Sale sale) {
        saleService.addSale(sale);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @GetMapping("/ventas")
    public List<Sale> getList() {
        return saleService.salesList();
    }

    @GetMapping("/ventas/{sale_code}")
    public ResponseEntity<Sale> getSale(@PathVariable Long sale_code) {
        return new ResponseEntity<>(saleService.getSaleById(sale_code), HttpStatus.OK);
    }

    @DeleteMapping("/ventas/{sale_code}")
    public ResponseEntity<String> deleteSale(@PathVariable Long sale_code) {
        saleService.deleteSale(sale_code);
        return new ResponseEntity<>("La venta se eliminó exitosamente", HttpStatus.OK);
    }

    @PutMapping("/ventas/editar")
    public ResponseEntity<Sale> editSale(@RequestBody Sale sale) {
        saleService.editSale(sale);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @PutMapping("/ventas/editar/{sale_code}")
    public ResponseEntity<Sale> editSale(@PathVariable Long sale_code,
                                         @RequestParam(required = false, name = "sale_code") Long new_code,
                                         @RequestParam(required = false, name = "sale_date")LocalDate new_date,
                                         @RequestParam(required = false, name = "total") Double new_total) {
        saleService.editSale(sale_code, new_code, new_date, new_total);
        Sale editedSale = saleService.getSaleById(sale_code);
        return new ResponseEntity<>(editedSale, HttpStatus.OK);
    }

    @PostMapping("/ventas/crear/calculo")
    public ResponseEntity<Sale> saveSaleCalculation(@RequestBody Sale sale) {
        saleService.saveSaleCalculation(sale);
        return new ResponseEntity<>(sale, HttpStatus.OK);
    }

    @GetMapping("/ventas/productos/{sale_code}")
    public ResponseEntity<SaleProductDTO> productsBySale(@PathVariable Long sale_code) {
        return new ResponseEntity<>(saleService.productSale(sale_code), HttpStatus.OK);
    }

    @GetMapping("/ventas/total/{fecha}")
    public ResponseEntity<SaleDayDTO> saleDay(@PathVariable String fecha) {
        return new ResponseEntity<>(saleService.saleDay(fecha), HttpStatus.OK);
    }
}
