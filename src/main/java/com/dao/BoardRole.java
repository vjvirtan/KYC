package com.dao;

import lombok.*;


@Setter
@Getter
@Builder
public class BoardRole extends UUIDGen {
  private String personId;
  private String boardRole;

}
