package com.dto.restDto;

import com.dto.*;
import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ValidationServiceDto(String key, String value, ValidationRuleDto validationRule) {

}
