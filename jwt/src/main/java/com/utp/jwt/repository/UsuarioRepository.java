package com.utp.jwt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utp.jwt.http.dto.user.UserListDto;
import com.utp.jwt.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  public List<UserListDto> findAllBy();

  public Optional<Usuario> findByCorreo(String correo);

}
