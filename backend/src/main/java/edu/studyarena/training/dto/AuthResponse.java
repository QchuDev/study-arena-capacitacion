package edu.studyarena.training.dto;

record AuthResponse(
  String token,
  String name,
  String email
) {}
