package com.robermejia.utp.controller;

import java.util.Optional;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.validation.BindingResult;
import com.robermejia.utp.services.CategoriaService;
import org.springframework.stereotype.Controller;
import com.robermejia.utp.entities.Categoria;
import org.springframework.ui.Model;
import jakarta.validation.Valid;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/administracion/categoria")
    public String index(Model model) {
        model.addAttribute("categorias", categoriaService.getCategorias());
        return "administracion-categoria";
    }

    @GetMapping("/administracion/categoria/nuevo")
    public String create(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "formulario-categorias";
    }

    @GetMapping("/administracion/categoria/{id}/editar")
    public String edit(@PathVariable() String id, Model model) {
        Optional<Categoria> categoria = categoriaService.getCategoriaById(Integer.parseInt(id));
        if (categoria.isPresent()) {
            model.addAttribute("categoria", categoria.get());
            return "formulario-categorias";
        }
        return "redirect:/administracion/categoria";
    }

    @PostMapping("/administracion/categoria/{id}/eliminar")
    public String destroy(@PathVariable() String id, Model model, RedirectAttributes redirectAttrs) {
        Optional<Categoria> categoria = categoriaService.getCategoriaById(Integer.parseInt(id));
        if (categoria.isPresent()) {
            try {
                categoriaService.deleteCategoria(categoria.get().getId());
                return "redirect:/administracion/categoria";
            } catch (Exception ex) {
                redirectAttrs.addFlashAttribute("mensaje",
                        "La categoría tiene productos asignados, no se puede eliminar");
                return "redirect:/administracion/categoria";
            }
        }
        return "redirect:/administracion/categoria";
    }

    @PostMapping("/categorias/guardar")
    public String store(@Valid Categoria categoria, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categoria", categoria);
            model.addAttribute("fieldErrors", bindingResult.getFieldErrors());
            model.addAttribute("mensajeError", "Por favor, complete todos los campos correctamente.");
            return "formulario-categorias";
        }
        categoriaService.guardarCategoria(categoria);
        return "redirect:/administracion/categoria";
    }

    @PostMapping("/categorias/editar")
    public String update(@Valid Categoria categoria, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categoria", categoria);
            model.addAttribute("fieldErrors", bindingResult.getFieldErrors());
            model.addAttribute("mensajeError", "Por favor, complete todos los campos correctamente.");
            return "formulario-editar-categorias";
        }
        categoriaService.guardarCategoria(categoria);
        return "redirect:/administracion/categoria";
    }
}
