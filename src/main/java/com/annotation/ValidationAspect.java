package com.annotation;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.*;

import com.service.*;

import lombok.*;

@Aspect
@Component
@RequiredArgsConstructor
public class ValidationAspect {
  private final ValidationService validationService;

  @Around("@annotation(Validation)")
  public Object before(ProceedingJoinPoint pj) throws Throwable {
    Object x[] = pj.getArgs();

    for (Object arg : x) {
      validationService.validateFields(arg);
    }

    return pj.proceed(x);
  }
}
