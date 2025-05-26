package com.utp.jwt.http.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utp.jwt.http.dto.auth.LoginRequestDTO;
import com.utp.jwt.http.dto.auth.SignInRequestDTO;
import com.utp.jwt.http.response.ApiResponse;
import com.utp.jwt.model.Usuario;
import com.utp.jwt.security.JWTService;
import com.utp.jwt.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

  private final JWTService jwtService;

  private final UsuarioService usuarioService;

  public AuthController(JWTService jwtService, UsuarioService usuarioService) {
    this.jwtService = jwtService;
    this.usuarioService = usuarioService;
  }

  @PostMapping("/sign-in")
  public ResponseEntity<ApiResponse<Usuario>> signIn(@Valid @RequestBody SignInRequestDTO request,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      Map<String, String> errors = new HashMap<>();
      for (FieldError error : bindingResult.getFieldErrors()) {
        errors.put(error.getField(), error.getDefaultMessage());
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.badRequest(null,
          errors.toString()));
    }

    Optional<Usuario> usuario = this.usuarioService.findByCorreo(request.getEmail());

    if (usuario.isPresent()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.notFound("Correo ya registrado"));
    }

    Usuario newUser = new Usuario();
    newUser.setNombre(request.getNombre());
    newUser.setCorreo(request.getEmail());
    newUser.setClave(request.getPassword());

    newUser = this.usuarioService.registrarUsuario(newUser);

    return ResponseEntity.ok().body(ApiResponse.success(newUser));
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<String>> login(@Valid @RequestBody LoginRequestDTO request,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      Map<String, String> errors = new HashMap<>();
      for (FieldError error : bindingResult.getFieldErrors()) {
        errors.put(error.getField(), error.getDefaultMessage());
      }
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.badRequest(null,
          errors.toString()));
    }

    Optional<Usuario> usuario = this.usuarioService.findByCorreo(request.getEmail());

    if (usuario.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.notFound("Credenciales incorrectas"));
    }

    if (!this.usuarioService.checkPassword(usuario.get().getClave(), request.getPassword())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponse.notFound("Credenciales incorrectas"));
    }

    String token = this.jwtService.generateToken(usuario.get());

    return ResponseEntity.ok().body(ApiResponse.success(token, "Login Exitoso"));
  }
}
