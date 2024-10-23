package com.controller;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.dto.*;
import com.service.*;

import lombok.*;

@CrossOrigin
@RestController
@RequestMapping("advice")
@RequiredArgsConstructor
public class AdviceController {

  private final FieldDictionaryService fieldDictionaryInterface;

  @GetMapping("/all")
  public ResponseEntity<Map<String, FieldDictionaryDto>> postMethodName() {
    System.out.println(" kysely ");
    return this.fieldDictionaryInterface.getAllFieldDictionaries();
  }

}
