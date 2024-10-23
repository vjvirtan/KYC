package com.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.dto.*;
import com.service.*;


@RestController
@RequestMapping("advice")

public class AdviceController {
  @Autowired
  private FieldDictionaryService fieldDictionaryInterface;


  @GetMapping("/all")
  public ResponseEntity<Map<String, FieldDictionaryDto>> postMethodName() {
    return this.fieldDictionaryInterface.getAllFieldDictionaries();
  }

}
