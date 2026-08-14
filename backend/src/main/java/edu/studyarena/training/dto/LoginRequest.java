package edu.studyarena.training.dto;

public record LoginRequest(
  String email,
  String password
) {}
