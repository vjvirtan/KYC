package com.controller.exeption;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;

import com.exeption.*;
import com.exeption.valid.*;

@ControllerAdvice
public class ExeptionHandlerer {
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<List<ValidationError<?, ?>>> handleValidationException(ValidationException validationExeption,
      WebRequest request) {

    return new ResponseEntity<List<ValidationError<?, ?>>>(HttpStatus.BAD_REQUEST);
  }
}
