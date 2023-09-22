package com.example.bazarApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class SaleDayDTO {
    private LocalDate date;
    private Double totalSum;
    private Double amounSale;

    public SaleDayDTO() {
    }

    public SaleDayDTO(LocalDate date, Double totalSum, Double amounSale) {
        this.date = date;
        this.totalSum = totalSum;
        this.amounSale = amounSale;
    }
}
