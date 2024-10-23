package com.exeption.valid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.dto.dto.*;

public class ValidationTest {
  @Test
  void testDataClass() {

  }

  @Test
  void testEquals() {

  }

  @Test
  public void testGetClass() {

    Class<?> testClass = null;
    try {
      testClass = Class.forName("com.temp." + BoardDto.class.getSimpleName() + "Valid");
      // BoardDtoValid.builder().name(null).build();
    } catch (ClassNotFoundException e1) {

      e1.printStackTrace();
    }
    // assertTrue(testClass.getSimpleName().contains(BoardDto.class.getSimpleName()),
    // " viesti ");
  }

  @Test
  void testHashCode() {

  }

  @Test
  void testJson() {

  }

  @Test
  void testToString() {

  }
}
