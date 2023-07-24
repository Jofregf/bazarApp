package com.example.bazarApp.service;

import com.example.bazarApp.model.Producto;
import com.example.bazarApp.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public Producto crearProducto(Producto producto) {
        Producto nuevoProducto = productoRepository.save(producto);
        return nuevoProducto;
    }

    @Override
    public List<Producto> listaProducto() {
        List<Producto> listaProductos = productoRepository.findAll();
        return listaProductos;
    }

    @Override
    public Producto traerProducto(Long codigo_producto) {
//        Producto productoById = productoRepository.findById(codigo_producto).orElse(null);
        Producto productoById = productoRepository.findById(codigo_producto)
                .orElseThrow(() -> new NoSuchElementException("No se encontró ningún producto con el código: " + codigo_producto));
        return productoById;
    }

    @Override
    public void eliminarProducto(Long codigo_producto) {
        Producto producto = this.traerProducto(codigo_producto);
        productoRepository.delete(producto);
    }

    @Override
    public Producto editarProducto(Long codigo_producto, Producto produ) {
        Producto producto = this.traerProducto(codigo_producto);
        producto.setNombre(produ.getNombre());
        producto.setMarca(produ.getMarca());
        producto.setCosto(produ.getCosto());
        producto.setCantidad_disponible(produ.getCantidad_disponible());
        producto.setCodigo_producto(codigo_producto);
        this.crearProducto(producto);
        System.out.println(producto.getCodigo_producto());
        return producto;
    }
}
