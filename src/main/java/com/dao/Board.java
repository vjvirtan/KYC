package com.dao;

import java.util.*;

import org.springframework.data.mongodb.core.mapping.*;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class Board extends UUIDGen {
  private String businessId;
  private ArrayList<BoardRole> roles;
  private int order;
}
