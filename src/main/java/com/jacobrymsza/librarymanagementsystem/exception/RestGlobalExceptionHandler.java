package com.jacobrymsza.librarymanagementsystem.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for handling application-wide exceptions.
 * This class provides centralized exception handling across all rest controllers
 * and returns appropriate HTTP responses for different exception types.
 */
@RestControllerAdvice
public class RestGlobalExceptionHandler {

  /**
   * Handles IllegalArgumentException thrown anywhere in the application.
   * Returns a BAD_REQUEST (400) response with the exception message.
   *
   * @param e the IllegalArgumentException that was thrown
   * @return ResponseEntity containing the error message and HTTP status
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }
}