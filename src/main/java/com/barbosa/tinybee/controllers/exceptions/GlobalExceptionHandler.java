package com.barbosa.tinybee.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.barbosa.tinybee.services.exceptions.LinkInactiveException;
import com.barbosa.tinybee.services.exceptions.LinkNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(LinkNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleLinkNotFound(LinkNotFoundException ex) {
    ErrorResponse error = new ErrorResponse(
        "LINK_NOT_FOUND",
        ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(LinkInactiveException.class)
  public ResponseEntity<ErrorResponse> handleLinkInactive(LinkInactiveException ex) {
    ErrorResponse error = new ErrorResponse(
        "LINK_INACTIVE",
        ex.getMessage());
    return ResponseEntity.status(HttpStatus.GONE).body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
    ErrorResponse error = new ErrorResponse(
        "INTERNAL_ERROR",
        "An unexpected error occurred");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

}
