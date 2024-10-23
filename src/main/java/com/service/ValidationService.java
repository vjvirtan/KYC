package com.service;

import java.lang.reflect.*;
import java.util.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import com.configuration.*;
import com.dto.*;
import com.dto.restDto.*;
import com.exeption.*;
import com.exeption.valid.*;
import com.interfece.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class ValidationService implements ValidationInterface {
  private final RestTemplate restTemplate;
  private final HttpHeaders headers;
  private final BaseConfigurationProperties baseConfigurationProperties;

  @SuppressWarnings("unchecked")
  @Override
  public boolean validateFields(Object dtoClass) {

    List<Response> responses = new ArrayList<>();

    for (Field field : dtoClass.getClass().getDeclaredFields()) {

      try {
        field.setAccessible(true);

        if (field.get(dtoClass) != null) {
          ResponseEntity<ValidationErrors> validationResponse = validateField(field.getName(), field.get(dtoClass));

          if (validationResponse.getBody().errors().size() > 0) {
            responses.add(new Response(field.getName(), new ValidationErrors(validationResponse.getBody().errors())));
          }
        }

      } catch (IllegalArgumentException | IllegalAccessException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    }

    if (responses.size() > 0) {
      throw new ValidationException(responses);
    }
    return true;

  }

  public <T> ResponseEntity<ValidationErrors> validateField(String fieldName, T value) {

    if (!FieldDictionaryInterface.fieldDictionary.containsKey(fieldName) || value == null) {
      return new ResponseEntity<ValidationErrors>(new ValidationErrors(new ArrayList<>()),
          HttpStatus.BAD_REQUEST);
    }
    ValidationRuleDto rule = FieldDictionaryInterface.fieldDictionary.get(fieldName).validationRule();
    HttpEntity<Object> entity = new HttpEntity<>(new ValidationServiceDto(fieldName, value.toString(), rule), headers);
    try {
      ResponseEntity<ValidationErrors> response = this.restTemplate.exchange(
          baseConfigurationProperties.URL() + "/validate", HttpMethod.POST,
          entity,
          ValidationErrors.class);

      return response;
    } catch (Exception e) {

      return new ResponseEntity<ValidationErrors>(new ValidationErrors(new ArrayList<>()),
          HttpStatus.BAD_REQUEST);
    }
  }

}
