package edu.studyarena.training.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public record MeetingResponse(
    String id,
    String name,
    String description,
    LocalDateTime dateTime,
    String jitsiRoomId,
    String ownerName,
    String ownerEmail,
    Instant createdAt
) {}
