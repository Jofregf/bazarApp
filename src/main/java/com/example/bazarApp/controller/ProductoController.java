package com.example.bazarApp.controller;

import com.example.bazarApp.model.Producto;
import com.example.bazarApp.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @PostMapping("/productos/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        productoService.crearProducto(producto);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @GetMapping("/productos")
    public List<Producto> listaProductos() {
        return productoService.listaProducto();
    }

    @GetMapping("/productos/{codigo_producto}")
    public ResponseEntity<Producto> mostrarProducto(@PathVariable Long codigo_producto) {
        Producto producto = productoService.traerProducto(codigo_producto);
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long codigo_producto) {
        productoService.eliminarProducto(codigo_producto);
        return new ResponseEntity<>("Producto eliminado exitosamente", HttpStatus.OK);
    }

    @PutMapping("/productos/editar/{codigo_producto}")
    public ResponseEntity<Producto> editarProducto(@PathVariable Long codigo_producto,
                                                   @RequestBody Producto produ) {

        productoService.editarProducto(codigo_producto, produ);
        Producto producto = this.mostrarProducto(codigo_producto).getBody();
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }
}
