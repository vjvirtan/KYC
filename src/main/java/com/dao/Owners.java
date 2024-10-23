package com.dao;

import java.util.*;

import org.springframework.data.mongodb.core.mapping.*;

import lombok.*;

@Document
@Builder
public class Owners extends UUIDGen {
  private String businessId;
  private List<Owner> owners;
}
