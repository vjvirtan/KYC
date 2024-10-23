package com.exeption;

import java.util.*;

import com.exeption.valid.*;

import lombok.*;

@Getter
public class ValidationException extends RuntimeException {
  private List<ValidationError<?, ?>> errorMessages;

  public ValidationException(List<ValidationError<?, ?>> messages) {
    super("Errors has occured: ");
    this.errorMessages = messages;
  }
}
