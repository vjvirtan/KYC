package com.interfece;

import java.util.*;

import org.springframework.http.*;

import com.dto.*;

public interface FieldDictionaryInterface {
  String BASE_URL = "http://localhost:8082";
  static Map<String, FieldDictionaryDto> fieldDictionary = new HashMap<>();

  FieldDictionaryDto getFieldDictionary(String field);

  ResponseEntity<Map<String, FieldDictionaryDto>> getAllFieldDictionaries()
      throws InstantiationException, IllegalArgumentException, SecurityException;
}
