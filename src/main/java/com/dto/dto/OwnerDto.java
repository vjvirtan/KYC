package com.dto.dto;

import java.util.*;
import java.util.stream.*;

import com.dto.*;
import com.interfece.*;
import com.service.*;

import lombok.*;

@Builder()
public record OwnerDto(String id, String personId, double percentage) implements RuledDtoInterface {

  public static final ArrayList<ValuePair<String, ValidationRuleDto>> customRules =
      // ALLOWS MULTIPLE BOARD MEMBERS : SHOULD BE FOR SUBSTITUTE ALSO
      Arrays.asList(new ValuePair<String, ValidationRuleDto>("percentage",
          ValidationRuleDto.builder()
              .unique(false)
              .min(0.00)
              .max(100)
              .build()))
          .stream().collect(Collectors.toCollection(ArrayList::new));

}
