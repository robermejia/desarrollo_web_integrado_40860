package com.utp.jwt.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.utp.jwt.model.Usuario;
import com.utp.jwt.repository.UsuarioRepository;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
    Optional<Usuario> usuarioOpt = this.usuarioRepository.findByCorreo(correo);

    if (usuarioOpt.isEmpty()) {
      throw new UsernameNotFoundException("Usuario no encontrado: " + correo);
    }

    Usuario usuario = usuarioOpt.get();

    return User.builder()
        .username(usuario.getCorreo())
        .password(usuario.getClave())
        .roles(usuario.getTipo())
        .build();
  }
}
