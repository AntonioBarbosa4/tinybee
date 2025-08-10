package com.barbosa.tinybee.services.exceptions;

public class LinkNotFoundException extends RuntimeException {
  public LinkNotFoundException(String shortHash) {
    super("Link not found: " + shortHash);
  }
}