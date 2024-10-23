package com.interfece;

import org.springframework.http.*;

public interface ValidationInterface {
  <T> ResponseEntity<?> validateField(String field, T value);

  boolean validateFields(Object dtoClass);

}
