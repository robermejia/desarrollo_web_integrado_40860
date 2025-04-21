package com.robermejia.utp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robermejia.utp.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    Usuario findByCorreo(String correo);
}
