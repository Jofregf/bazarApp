package com.example.bazarApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo_venta;
    private LocalDate fecha_venta;
    private Double total;

    @OneToMany
    private List<Producto> listaProductos;
    @OneToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente unCliente;

    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }
}
