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
  private final com.configuration.BaseConfigurationProperties baseConfigurationProperties;
  private final RestTemplate restTemplate;
  private final HttpHeaders headers;

  public FieldDictionaryDto getFieldDictionary(String field) {
    if (fieldDictionary.containsKey(field)) {
      return fieldDictionary.get(field);
    }

    Map<String, Object> body = new HashMap<>();
    body.put("key", field);
    // TODO: BEAN ?

    // TODO: IF NEEDED
    // headers.set("Authorization", "Bearer " + token);

    HttpEntity<Object> entity = new HttpEntity<>(GeneralService.bodyBuilder(body), headers);
    ResponseEntity<FieldDictionaryDto> response = restTemplate.exchange(
        baseConfigurationProperties.URL() + "/fieldRules",
        HttpMethod.POST,
        entity, FieldDictionaryDto.class);

    if (response.getStatusCode().is2xxSuccessful()) {
      if (response.getBody().validationRule() == null) {
      }
      return response.getBody();
    } else {
      // TODO: DO SPECIFIC EXCEPTION HANDLERER
      throw new IllegalAccessError();
    }
  }

  Map<String, FieldDictionaryDto> getFieldDictionary()
      throws IOException, ClassNotFoundException, InstantiationException, IllegalArgumentException, SecurityException {
    addGeneralFieldsToDictionary();
    iterateDtos();

    return fieldDictionary;
  }

  @SuppressWarnings("unchecked")
  private void iterateDtos()
      throws ClassNotFoundException, IOException, InstantiationException, IllegalArgumentException, SecurityException {

    File folder = new File(baseConfigurationProperties.FILE_PATH());

    if (folder.isDirectory()) {
      File[] files = folder.listFiles();
      for (File file : files) {
        if (file.getName().contains("$")) {
          continue;
        }

        if (file.isFile() && file.getName().endsWith(".java") || file.getName().endsWith(".class")) {
          int nameCutter = file.getName().endsWith(".java") ? 5 : 6;
          Class<?> dtoClass = Class
              .forName("com.dto.dto." + file.getName().substring(0, file.getName().length() - nameCutter));
          try {
            // DIRECT dtoClass.getField(...) APPROACH CAUSE EXECPTION WEHEN NOT EXISTS...
            // FIELD IS OPTIONAL...MORE PROBLEMS IF CALUE NULL; POSSIBLE BUG?
            Field customRules = null;

            for (Field field : dtoClass.getFields()) {
              if (field.getName().equals("customRules")) {
                customRules = field;
              }
            }

            // ITERATE POSSIBLE CUSTOM RULES.
            for (Field field : dtoClass.getDeclaredFields()) {
              if (field.getName().equals("customRules")) {
                continue;
              }
              FieldDictionaryDto response = getFieldDictionary(field.getName());

              if (customRules != null) {
                for (ValuePair<String, ValidationRuleDto> rule : (ArrayList<ValuePair<String, ValidationRuleDto>>) customRules
                    .get(null)) {
                  FieldDictionaryDto fieldDictionaryInstance = FieldDictionaryDto.builder()
                      .categories(response.categories())
                      .key(response.key())
                      .id(response.id())
                      .translations(response.translations())
                      // ADD CUSTOM RULE HERE TO FIELDDICTIONARY
                      .validationRule(doCustomRule(rule.value(), response.validationRule()))
                      .build();
                  if (fieldDictionaryInstance.validationRule() == null) {
                  }
                  fieldDictionary.put(field.getName(), fieldDictionaryInstance);
                }

              } else {
                fieldDictionary.putIfAbsent(field.getName(), response);
              }

            }
          } catch (IllegalAccessException e) {
            // TODO: DO CUSTOM EXC
            e.printStackTrace();
          }

        }
      }
    }

  }

  private void addGeneralFieldsToDictionary() {
    Arrays.asList("headOfBoard", "ceo", "boardMember",
        "deputyBoardMember", "systemId", "tradeRegistryInfo", "nationality", "firstname", "lastname", "companyName")
        .forEach(e -> {
          fieldDictionary.putIfAbsent(e, getFieldDictionary(e));
        });
  }

  private ValidationRuleDto doCustomRule(ValidationRuleDto custom, ValidationRuleDto defaultRule) {
    if (defaultRule == null) {
      return null;
    }

    return ValidationRuleDto.builder()
        .allowedChars(custom.allowedChars() == null ? defaultRule.allowedChars() : custom.allowedChars())
        .mandatory(custom.mandatory() == null ? defaultRule.mandatory() : custom.mandatory())
        .unique(custom.unique() == null ? defaultRule.unique() : custom.unique())
        // TODO: CONSIDER CHANGE TO OBJECT FROM PRIMITIVE: OBJECTs HAVE a NULL!!!
        .min(custom.min() == 0 ? defaultRule.min() : custom.min())
        .max(custom.max() == 0 ? defaultRule.max() : custom.max())
        .subStrings(custom.subStrings() == null ? defaultRule.subStrings() : custom.subStrings())
        .build();
  }

  @Override
  public ResponseEntity<Map<String, FieldDictionaryDto>> getAllFieldDictionaries() {

    try {
        addGeneralFieldsToDictionary();
        iterateDtos();

      } catch (ClassNotFoundException | IOException | InstantiationException | IllegalArgumentException
          | SecurityException e) {

        e.printStackTrace();
      }
    return new ResponseEntity<>(fieldDictionary, HttpStatus.OK);
  }
}
