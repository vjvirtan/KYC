package com.dao;

import org.springframework.data.mongodb.core.mapping.*;

import lombok.*;

@Document
@Builder
@Getter
public class BeneficialOwner {
  private String personId;
}
