package com.dto.dto;

import java.util.*;
import java.util.stream.*;

import com.dto.*;
import com.interfece.*;
import com.service.*;
import lombok.*;

@Builder
public record BoardDto(String id, String businessId, ArrayList<BoardRoleDto> boardRoles)
    implements RuledDtoInterface {

  public ArrayList<ValuePair<String, ValidationRuleDto>> customRules() {
    // TODO: BAD

    // ALLOWS MULTIPLE BOARD MEMBERS : SHOULD BE FOR SUBSTITUTE ALSO
    return Arrays.asList(new ValuePair<String, ValidationRuleDto>("boardMember",
        ValidationRuleDto.builder()
            .unique(false)
            .build()),
        new ValuePair<String, ValidationRuleDto>("subsituteBoard",
            ValidationRuleDto.builder()
                .unique(false)
                .build()))
        .stream().collect(Collectors.toCollection(ArrayList::new));
  }
}
