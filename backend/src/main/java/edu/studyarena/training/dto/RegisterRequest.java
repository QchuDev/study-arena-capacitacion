package edu.studyarena.training.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
  @NotBlank(message = "El nombre es obligatorio") String name,
  @NotBlank(message = "El email es obligatorio") @Email(message = "Email invalido") String email,
  @NotBlank(message = "La contraseña es obligatorio") String password
) {}


