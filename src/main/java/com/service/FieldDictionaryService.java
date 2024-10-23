package com.service;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import com.dto.*;
import com.interfece.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class FieldDictionaryService implements FieldDictionaryInterface {
  private final RestTemplate restTemplate;
  private final HttpHeaders headers;

  public FieldDictionaryDto getFieldDictionary(String field) {
    Map<String, Object> body = new HashMap<>();
    body.put("key", field);
    // TODO: BEAN ?

    // TODO: IF NEEDED
    // headers.set("Authorization", "Bearer " + token);

    HttpEntity<Object> entity = new HttpEntity<>(GeneralService.bodyBuilder(body), headers);
    ResponseEntity<FieldDictionaryDto> response = restTemplate.exchange(BASE_URL + "/fieldRules", HttpMethod.POST,
        entity, FieldDictionaryDto.class);
    if (response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    } else {
      // TODO: DO SPECIFIC EXCEPTION HANDLERER
      throw new IllegalAccessError();
    }
  }

  Map<String, FieldDictionaryDto> getFieldDictionary()
      throws IOException, ClassNotFoundException, InstantiationException, IllegalArgumentException, SecurityException {
    if (fieldDictionary.size() == 0) {
      iterateDtos();
      addGeneralFieldsToDictionary();
    }
    return fieldDictionary;
  }

  @SuppressWarnings("unchecked")
  private void iterateDtos()
      throws ClassNotFoundException, IOException, InstantiationException, IllegalArgumentException, SecurityException {

    String path = "src/main/java/com/dto/dto";
    File folder = new File(path);

    if (folder.isDirectory()) {
      File[] files = folder.listFiles();
      for (File file : files) {
        Class<?> dtoClass = Class
            .forName("com.dto.dto." + file.getName().substring(0, file.getName().length() - 5));
        if (file.isFile() && file.getName().endsWith(".java")) {

          try {
            for (Method m : dtoClass.getDeclaredMethods()) {
              System.out.println(m.toString());
            }
            Method customRuleMethod = null;
            try {
              // THIS CHECK IF CUSTOM RULES EXISTS
              customRuleMethod = dtoClass.getDeclaredMethod("customRules");
            } catch (Exception e) {

            }
            // ITERATE POSSIBLE CUSTOM RULES.
            for (Field field : dtoClass.getDeclaredFields()) {
              System.out.println(" FIELD NAME " + field.getName());
              FieldDictionaryDto response = getFieldDictionary(field.getName());

              if (customRuleMethod != null) {
                // TODO: UNNESSARY INSTANCE CREATING. MOVE UP
                List<ValuePair<String, ValidationRuleDto>> customRules = (List<ValuePair<String, ValidationRuleDto>>) customRuleMethod
                    .invoke(dtoClass.getDeclaredConstructor().newInstance());
                ValuePair<String, ValidationRuleDto> customRule = isCustomRule(field.getName(), customRules);
                if (customRule == null) {
                  fieldDictionary.putIfAbsent(field.getName(), response);
                } else {
                  FieldDictionaryDto fdd = FieldDictionaryDto.builder()
                      .categories(response.categories())
                      .key(response.key())
                      .id(response.id())
                      .translations(response.translations())
                      .validationRule(doCustomRule(customRule.value(), response.validationRule()))
                      .build();
                  fieldDictionary.put(field.getName(), fdd);
                }
              } else {
                fieldDictionary.putIfAbsent(field.getName(), response);
              }

            }
          } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // TODO: DO CUSTOM EXC
            e.printStackTrace();
          }

        }
      }
    }

  }

  private void addGeneralFieldsToDictionary() {
    Arrays.asList("headOfBoard", "ceo", "boardMember",
        "deputyBoardMember", "systemId").forEach(e -> {
          System.out.println(" move me  " + e + " " + fieldDictionary.size());
          fieldDictionary.putIfAbsent(e, getFieldDictionary(e));
        });
    fieldDictionary.values().forEach(e -> System.out.println(e.key().toString()));
  }

  private ValuePair<String, ValidationRuleDto> isCustomRule(String field,
      List<ValuePair<String, ValidationRuleDto>> customRules) {
    for (ValuePair<String, ValidationRuleDto> pair : customRules) {
      if (pair.key().equals(field)) {
        return pair;
      }
    }
    return null;
  }

  private ValidationRuleDto doCustomRule(ValidationRuleDto custom, ValidationRuleDto defaultRule) {
    return ValidationRuleDto.builder()
        .allowedChars(custom.allowedChars() == null ? defaultRule.allowedChars() : custom.allowedChars())
        .mandatory(custom.mandatory() == null ? defaultRule.mandatory() : custom.mandatory())
        .unique(custom.unique() == null ? defaultRule.unique() : custom.unique())
        .min(custom.min() == null ? defaultRule.min() : custom.min())
        .max(custom.max() == null ? defaultRule.max() : custom.max())
        .subStrings(custom.subStrings() == null ? defaultRule.subStrings() : custom.subStrings())
        .build();
  }

  @Override
  public ResponseEntity<Map<String, FieldDictionaryDto>> getAllFieldDictionaries() {

    if (fieldDictionary.size() == 0) {
      try {
        iterateDtos();
        addGeneralFieldsToDictionary();
      } catch (ClassNotFoundException | IOException | InstantiationException | IllegalArgumentException
          | SecurityException e) {

        throw new IllegalAccessError();
      }
    }
    return new ResponseEntity<>(fieldDictionary, HttpStatus.OK);
  }
}
