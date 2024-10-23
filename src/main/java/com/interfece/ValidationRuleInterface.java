package com.interfece;

import org.springframework.stereotype.*;
import com.validation.*;

@Component
public interface ValidationRuleInterface {

  ValidationRule nameRule();

  ValidationRule orderRule();
}
