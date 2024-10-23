package com.service;

import java.lang.reflect.*;
import java.util.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import com.dto.*;
import com.exeption.*;
import com.exeption.valid.*;
import com.interfece.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class ValidationService implements ValidationInterface {
  private final RestTemplate restTemplate;
  private final HttpHeaders headers;
  private final Map<String, FieldDictionaryDto> fieldDictionary;

  @Override
  public boolean validateFields(Object dtoClass) {
    List<ValidationError<?, ?>> errors = new ArrayList<>();
    for (Field field : dtoClass.getClass().getDeclaredFields()) {
      ResponseEntity<Boolean> response = validateField(field.getName());
      if (response.getBody() != null || !response.getStatusCode().is2xxSuccessful()) {
        errors.add(new ValidationError<String, String>(field.getName(), "", ""));
      }
    }
    if (errors.size() == 0) {
      return true;
    }
    // TODO: GET ERRORS AND
    throw new ValidationException(
        Arrays.asList(new ValidationError<String, String>("FIX THIS ERROR ", " INPUTS DETAILED ", "null")));

  }

  public ResponseEntity<Boolean> validateField(String fieldName) {
    if (!this.fieldDictionary.containsKey(fieldName)) {
      throw new ValidationException(Arrays
          .asList(new ValidationError<String, String>("DICTIONARY NOT FOUND FOR " + fieldName, "Dictionary", "NULL")));
    }
    ValidationRuleDto rule = this.fieldDictionary.get(fieldName).validationRule();
    Map<String, Object> body = new HashMap<>();
    body.put("key", rule);

    body.put("validationRule", rule);
    HttpEntity<Object> entity = new HttpEntity<>(GeneralService.bodyBuilder(body), headers);
    ResponseEntity<Boolean> response = this.restTemplate.exchange(BASE_URL + "/validate", HttpMethod.POST, entity,
        Boolean.class);

    return response;
  }

}
