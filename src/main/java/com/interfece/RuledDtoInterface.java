package com.interfece;

import java.util.*;

import com.dto.*;
import com.service.*;

public interface RuledDtoInterface {
  // OVERRIDE THIS FOR CUSTOM RULES: RULE ONLY CUSTOM FIELDS, LET REST BE NULL!
  default List<ValuePair<String, ValidationRuleDto>> customRules() {
    return null;
  };
}
