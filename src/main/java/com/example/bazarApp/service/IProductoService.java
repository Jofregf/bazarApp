package com.example.bazarApp.service;

import com.example.bazarApp.model.Producto;

import java.util.List;

public interface IProductoService {

    public Producto crearProducto(Producto producto);

    public List<Producto> listaProducto();

    public Producto traerProducto(Long codigo_producto);

    public void eliminarProducto(Long codigo_producto);

    public Producto editarProducto(Long codigo_producto, Producto producto);
}
