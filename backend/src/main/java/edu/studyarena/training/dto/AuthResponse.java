package edu.studyarena.training.dto;

public record AuthResponse(
  String token,
  String name,
  String email
) {}
