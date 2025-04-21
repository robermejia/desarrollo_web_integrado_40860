package com.robermejia.utp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.robermejia.utp.entities.Producto;
import com.robermejia.utp.repositories.ProductoRepository;
import com.robermejia.utp.services.CategoriaService;
import com.robermejia.utp.services.ProductoService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
public class AdminController {

    private final ProductoRepository productoRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/administracion")
    public String admin(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "administracion";
    }

    @GetMapping("/administracion/nuevo-producto")
    public String nuevoProducto(Model model) {
        Producto producto = new Producto();
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "formulario-productos";
    }

    @GetMapping("/administracion/editar-producto/{id}")
    public String editarProducto(@PathVariable() String id, Model model) {
        Optional<Producto> producto = productoRepository.findById(Integer.parseInt(id));
        if(producto.isPresent()) {
            model.addAttribute("producto", producto);
            return "formulario-editar-productos";
        }
        return "redirect:/administracion";
    }

    @PostMapping("/administracion/eliminar/{id}")
    public String postMethodName(@PathVariable() String id, RedirectAttributes redirectAttrs) {
        Optional<Producto> producto = productoRepository.findById(Integer.parseInt(id));
        if(producto.isPresent()) {
            productoService.deleteProducto(producto.get().getId());
            redirectAttrs.addFlashAttribute("mensaje", "Producto eliminado con éxito");
            return "redirect:/administracion";
        }

        redirectAttrs.addFlashAttribute("mensajeError", "Error, no encontro el producto");
        return "redirect:/administracion";
    }
}