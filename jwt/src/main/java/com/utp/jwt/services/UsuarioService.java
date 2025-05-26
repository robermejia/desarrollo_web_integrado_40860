package com.utp.jwt.services;

import java.util.List;
import java.util.Optional;

import com.utp.jwt.http.dto.user.UserListDto;
import com.utp.jwt.model.Usuario;

public interface UsuarioService {

  public List<UserListDto> listAll();

  public Optional<Usuario> findById(Long id);

  public Optional<Usuario> findByCorreo(String correo);

  public Usuario registrarUsuario(Usuario usuario);

  public boolean checkPassword(String hashedPassword, String password);

}
