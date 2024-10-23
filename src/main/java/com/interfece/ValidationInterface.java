package com.interfece;

import org.springframework.http.*;

public interface ValidationInterface {
  String BASE_URL = "http://localhost:8082";

  ResponseEntity<Boolean> validateField(String field);

  boolean validateFields(Object dtoClass);

}
