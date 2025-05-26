package com.utp.jwt.http.dto.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "tipo", "correo", "nombre" })
public interface UserListDto {

  Long getId();

  String getTipo();

  String getCorreo();

  String getNombre();

}
