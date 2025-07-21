package com.utp.technology.http.dto.product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StoreProductDTO {

  @NotBlank(message = "El nombre es obligatorio")
  @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
  private String nombre;

  @NotBlank(message = "La imagen es obligatoria")
  private String imagen;

  @NotNull
  @Positive(message = "El precio debe ser positivo")
  @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0.00")
  private Double precio;

  @NotNull
  @Min(value = 1, message = "El stock debe ser mayor o igual a 0")
  private Integer stock;

}
