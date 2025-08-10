package com.barbosa.tinybee.controllers.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(
    String error,
    String message,
    LocalDateTime timestamp) {
  public ErrorResponse(String error, String message) {
    this(error, message, LocalDateTime.now());
  }

}
