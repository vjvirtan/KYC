package com.exeption;

import java.util.*;

import lombok.*;

@Getter

public class ValidationException extends RuntimeException {
  private List<Response> errorResponse;

  public ValidationException(List<Response> responses) {
    super("Errors has occured: ");
    if (errorResponse == null) {
      errorResponse = responses;
    }

  }

}
