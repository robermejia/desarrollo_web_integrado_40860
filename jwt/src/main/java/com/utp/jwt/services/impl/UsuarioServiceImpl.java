package com.utp.jwt.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.utp.jwt.http.dto.user.UserListDto;
import com.utp.jwt.model.Usuario;
import com.utp.jwt.repository.UsuarioRepository;
import com.utp.jwt.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioRepository usuarioRepository;

  private final PasswordEncoder passwordEncoder;

  public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
    this.usuarioRepository = usuarioRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public List<UserListDto> listAll() {
    return this.usuarioRepository.findAllBy();
  }

  @Override
  public Optional<Usuario> findById(Long id) {
    return this.usuarioRepository.findById(id);
  }

  @Override
  public Optional<Usuario> findByCorreo(String correo) {
    return this.usuarioRepository.findByCorreo(correo);
  }

  @Override
  public Usuario registrarUsuario(Usuario usuario) {
    String hashedPassword = this.passwordEncoder.encode(usuario.getClave());
    usuario.setClave(hashedPassword);

    if (usuario.getTipo() == null) {
      usuario.setTipo("user");
    }

    return this.usuarioRepository.save(usuario);
  }

  @Override
  public boolean checkPassword(String hashedPassword, String password) {
    return this.passwordEncoder.matches(password, hashedPassword);
  }

}
