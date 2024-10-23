package com.dao;

import org.springframework.data.mongodb.core.mapping.*;

import lombok.*;

@Getter
@Setter
@Builder
@Document
public class Representative extends UUIDGen {
  private String name;
  private int order;
}
