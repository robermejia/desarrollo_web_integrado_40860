package com.utp.technology.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.utp.technology.http.dto.auth.SignUpRequestDTO;
import com.utp.technology.http.dto.user.UserListDto;
import com.utp.technology.model.Cliente;
import com.utp.technology.model.Rol;
import com.utp.technology.model.Usuario;
import com.utp.technology.repository.ClienteRepository;
import com.utp.technology.repository.RolRepository;
import com.utp.technology.repository.UsuarioRepository;
import com.utp.technology.services.UsuarioService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

  private final UsuarioRepository usuarioRepository;
  private final ClienteRepository clienteRepository;
  private final PasswordEncoder passwordEncoder;
  private final RolRepository rolRepository;

  @Override
  public List<UserListDto> listAll() {
    return this.usuarioRepository.listAll();
  }

  @Override
  public Optional<Usuario> findById(Integer id) {
    return this.usuarioRepository.findById(id);
  }

  @Override
  public Optional<Usuario> findByCorreo(String correo) {
    return this.usuarioRepository.findByCorreo(correo);
  }

  @Override
  public Usuario registrarUsuario(SignUpRequestDTO data) {

    Optional<Cliente> clienteOpt = this.clienteRepository.findByDni(data.getDni());
    if(clienteOpt.isPresent()){
      throw new IllegalArgumentException("El dni ya esta siendo usado.");
    }

    Usuario usuario = new Usuario();
    usuario.setNombre(data.getNombre());
    usuario.setCorreo(data.getEmail());
    usuario.setClave(data.getPassword());
    Rol rol = new Rol();
    rol.setIdRol(3);
    usuario.setRol(rol);

    String hashedPassword = this.passwordEncoder.encode(data.getPassword());
    usuario.setClave(hashedPassword);

    if (usuario.getRol() == null) {
      Rol rolUser = rolRepository.findByNombreRol("USER")
          .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));
      usuario.setRol(rolUser);
    }

    usuario = this.usuarioRepository.save(usuario);

    Cliente cliente = new Cliente();
    cliente.setNombre(data.getNombre());
    cliente.setApellido(data.getApellido());
    cliente.setDni(data.getDni());
    cliente.setTelefono(data.getTelefono());
    cliente.setCorreo(data.getEmail());
    cliente.setUsuario(usuario);

    this.clienteRepository.save(cliente);

    return usuario;
  }

  @Override
  public boolean checkPassword(String hashedPassword, String password) {
    return this.passwordEncoder.matches(password, hashedPassword);
  }

}
