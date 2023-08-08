package com.coppel.mx.model.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class SumaDto {

  @NotNull(message = "el numero 1 no debe ser nulo")
  @Min(value = 0, message = "numero 1 no se aceptan numero menores a 0")
  private Integer numero1;

  @NotNull(message = "el numero 2 no debe ser nulo")
  @Min(value = 0, message = "numero 2 no se aceptan numero menores a 0")
  private Integer numero2;
}
