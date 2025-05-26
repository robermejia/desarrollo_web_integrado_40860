package com.utp.jwt.http.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.jwt.http.dto.product.StoreProductDTO;
import com.utp.jwt.http.response.ApiResponse;
import com.utp.jwt.model.Producto;
import com.utp.jwt.services.ProductoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/producto")
public class ProductController {

  private final ProductoService productoService;

  @GetMapping
  public ResponseEntity<ApiResponse<List<Producto>>> index() {
    return ResponseEntity.ok().body(ApiResponse.success(this.productoService.listAll()));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Producto>> store(@Valid @RequestBody StoreProductDTO request,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      Map<String, String> errors = new HashMap<>();
      for (FieldError error : bindingResult.getFieldErrors()) {
        errors.put(error.getField(), error.getDefaultMessage());
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.badRequest(null,
          errors.toString()));
    }

    Producto producto = this.productoService.guardar(request);

    return ResponseEntity.ok().body(ApiResponse.success(producto));
  }

}
