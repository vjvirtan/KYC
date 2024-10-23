package com.dto;

import java.util.*;

import com.service.*;

import lombok.*;

@Builder
public record FieldDictionaryDto(String id, String key, List<CategoryDto> categories,
        List<ValuePair<String, String>> translations,
        ValidationRuleDto validationRule) {

}
