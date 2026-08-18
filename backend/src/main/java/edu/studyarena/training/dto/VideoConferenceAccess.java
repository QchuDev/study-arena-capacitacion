package edu.studyarena.training.dto;

import java.time.Instant;

public record VideoConferenceAccess(
    String domain,
    String roomName,
    String token,
    Instant expiresAt
) {}
