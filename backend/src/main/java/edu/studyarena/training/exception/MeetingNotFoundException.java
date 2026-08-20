package edu.studyarena.training.exception;

public class MeetingNotFoundException extends RuntimeException {
  public MeetingNotFoundException(String meetingId) {
    super(meetingId);
  }
}
