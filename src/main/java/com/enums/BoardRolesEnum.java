package com.enums;

import lombok.*;

@Getter
public enum BoardRolesEnum {
  CEO("ceo"),
  HEAD_OF_BOARD("headOfBoard"),
  BOARD_MEMBER("boardMember"),
  DEPUTY_BOARD_MEMBER("deputyBoardMember");

  private String value;

  private BoardRolesEnum(String value) {
    this.value = value;
  }

}
