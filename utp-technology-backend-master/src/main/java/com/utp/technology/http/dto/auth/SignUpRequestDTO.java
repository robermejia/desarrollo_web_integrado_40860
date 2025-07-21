package com.utp.technology.http.dto.auth;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignUpRequestDTO {

  @Size(max = 100)
  @Email(message = "Debe ser un formato de correo electrónico válido")
  @NotBlank(message = "El correo es obligatorio")
  private String email;

  @NotBlank(message = "La contraseña es obligatoria")
  private String password;

  @Size(max = 50)
  @NotBlank(message = "El nombre es obligatorio")
  private String nombre;

  @Size(max = 50)
  @NotBlank(message = "El apellido es obligatorio")
  private String apellido;

  @NotBlank(message = "El documento es obligatorio")
  @Digits(fraction = 0, integer = 8)
  private String dni;

  @NotBlank(message = "El celular es obligatorio")
  @Digits(fraction = 0, integer = 9)
  private String telefono;

}
