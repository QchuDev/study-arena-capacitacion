package edu.studyarena.training.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateMeetingRequest(
    @NotBlank String name,
    String description,
    @NotNull LocalDateTime dateTime
) {}
