package com.dto;

import java.util.*;

import com.fasterxml.jackson.annotation.*;
import com.service.*;

import lombok.*;

// USE ONLY OBJECTS IN FIELDS BECAUSE NEED OF NULL VALUES: DO NOT USE PRIMITIVES!!!
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record ValidationRuleDto(Boolean mandatory,
    Boolean unique,
        double max,
        double min,
    String allowedChars,
    List<ValuePair<Integer, String>> subStrings) {

}
