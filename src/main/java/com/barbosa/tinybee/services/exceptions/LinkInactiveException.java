package com.barbosa.tinybee.services.exceptions;

public class LinkInactiveException extends RuntimeException {

  public LinkInactiveException(String shortHash) {
    super("Link is inactive: " + shortHash);
  }
}