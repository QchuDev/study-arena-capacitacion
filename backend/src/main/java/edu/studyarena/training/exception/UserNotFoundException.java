package edu.studyarena.training.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String email) {
    super(email);
  }
}
