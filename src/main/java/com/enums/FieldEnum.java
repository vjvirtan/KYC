package com.enums;

public enum FieldEnum {
  NAME("^[a-zA-Z]+$"),
  DECIMAL("^\\d+([.,]\\d+)?$"),
  WHOLE_NUMBER("^[0-9]+$");

  private String value;

  FieldEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
