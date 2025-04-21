package com.robermejia.utp.controller;

import com.robermejia.utp.entities.Producto;
import com.robermejia.utp.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/productos")
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", productoService.getProductos());
        return "productos";
    }

    @GetMapping("/productos/nuevo")
    public String nuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "nuevo_producto";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(@Valid Producto producto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("producto", producto);
            model.addAttribute("fieldErrors", bindingResult.getFieldErrors());
            model.addAttribute("mensajeError", "Por favor, complete todos los campos correctamente.");
            return "formulario-productos";
        } 

        productoService.guardarProducto(producto);
        return "redirect:/administracion";
    }

    @PostMapping("/productos/editar")
    public String editarProducto(@Valid Producto producto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // bindingResult.getAllErrors();
            model.addAttribute("producto", producto);
            model.addAttribute("fieldErrors", bindingResult.getFieldErrors());
            model.addAttribute("mensajeError", "Por favor, complete todos los campos correctamente.");
            return "formulario-editar-productos";
        }

        productoService.editarProducto(producto);
        return "redirect:/administracion";
    }
}