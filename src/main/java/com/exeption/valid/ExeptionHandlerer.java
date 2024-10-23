package com.exeption.valid;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;

import com.exeption.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

@ControllerAdvice
public class ExeptionHandlerer {

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<List<Response>> handleValidationException(
      ValidationException validationException, WebRequest request) throws JsonProcessingException {
    var mapped = new ObjectMapper();
    return new ResponseEntity<List<Response>>(validationException.getErrorResponse(), HttpStatus.NOT_ACCEPTABLE);
  }
}
