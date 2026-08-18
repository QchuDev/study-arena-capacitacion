package edu.studyarena.training.controller;

import edu.studyarena.training.dto.CreateMeetingRequest;
import edu.studyarena.training.dto.MeetingResponse;
import edu.studyarena.training.dto.VideoConferenceAccess;

import edu.studyarena.training.service.MeetingService;
import edu.studyarena.training.service.VideoConferenceAccessService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    private final MeetingService meetingService;
    private final VideoConferenceAccessService videoConferenceAccessService;

  public MeetingController(
      MeetingService meetingService,
      VideoConferenceAccessService videoConferenceAccessService
  ) {
      this.meetingService = meetingService;
      this.videoConferenceAccessService = videoConferenceAccessService;
  }

  @PostMapping
  public ResponseEntity<MeetingResponse> create(
          @Valid @RequestBody CreateMeetingRequest request,
          Authentication authentication) {

      String email = authentication.getName();
      MeetingResponse response = meetingService.create(request, email);
      return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<List<MeetingResponse>> findAll() {
      return ResponseEntity.ok(meetingService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<MeetingResponse> findById(@PathVariable String id) {
      return ResponseEntity.ok(meetingService.findById(id));
  }

  @PostMapping("/{meetingId}/access")
  public ResponseEntity<VideoConferenceAccess> getAccess(
      @PathVariable String meetingId,
      Authentication authentication
  ) {
    String email = authentication.getName();
    VideoConferenceAccess access = videoConferenceAccessService.generateAccess(meetingId, email);
    return ResponseEntity.ok(access);
  } 

}
