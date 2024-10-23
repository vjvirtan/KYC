package com.validation;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import java.util.regex.*;

import com.exeption.valid.*;
import com.service.*;

import lombok.*;

@Builder
public record ValidationRule(
    boolean mandatory,
    boolean unique,
    double max,
    double min,
    String allowedChars,
    List<ValuePair<Integer, String>> subStrings) {

  public <T extends Serializable> ValuePair<Boolean, ValidationError<String, String>> testMandatory(T value) {
    if (this.mandatory) {
      if (value == null || value.toString().equals("")) {
        return new ValuePair<Boolean, ValidationError<String, String>>(false,
            new ValidationError<String, String>("too short", value.toString(),
                this.allowedChars.toLowerCase()));
      }
    }
    return new ValuePair<Boolean, ValidationError<String, String>>(true, null);
  }

  public boolean testUnique(Class<?> repo, Class<?> valueClass, Field validationField) {
    boolean response = true;
    String fieldNameWithCapitalFirst = validationField.getName().substring(0, 1).toUpperCase()
        + validationField.getName().substring(1);

    try {
      var value = (Serializable) validationField.get(valueClass);
      Method m = repo.getClass().getMethod("existsBy" + fieldNameWithCapitalFirst);
      response = !(boolean) m.invoke(repo, value);

      if (this.unique) {
        if (value instanceof Number) {

        } else if (value instanceof String) {

        }

        return response;
      }
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return true;
  }

  public <T extends Serializable> ValuePair<Boolean, ValidationError<String, String>> testAllowedChars(T value) {
    boolean response = Pattern.matches(this.allowedChars(), value.toString());

    return response ? new ValuePair<Boolean, ValidationError<String, String>>(true, null)
        : new ValuePair<Boolean, ValidationError<String, String>>(false,
            new ValidationError<String, String>("allowedChars", value.toString(),
                this.allowedChars.toLowerCase()));
  }

  public <T extends Serializable> ValuePair<Boolean, ValidationError<String, String>> testMin(T value) {
    boolean response = value instanceof Number ? Double.parseDouble(value.toString()) >= this.min
        : value.toString().length() >= this.min;

    return response ? new ValuePair<Boolean, ValidationError<String, String>>(true, null)
        : new ValuePair<Boolean, ValidationError<String, String>>(false,
            new ValidationError<String, String>("too short", value.toString(),
                this.allowedChars.toLowerCase()));
  }

  public <T extends Serializable> ValuePair<Boolean, ValidationError<String, String>> testMax(T value) {
    boolean response = value instanceof Number ? Double.parseDouble(value.toString()) <= this.min
        : value.toString().length() <= this.min;
    return response ? new ValuePair<Boolean, ValidationError<String, String>>(true, null)
        : new ValuePair<Boolean, ValidationError<String, String>>(false,
            new ValidationError<String, String>("Input is too long, ", value.toString(),
                this.allowedChars.toLowerCase()));
  }

  public <T extends Serializable> ValuePair<Boolean, ValidationError<String, String>> testSubstring(T value) {
    for (ValuePair<Integer, String> e : this.subStrings) {
      if (Pattern.matches(e.value(), value.toString().substring(e.key(), e.key() + 1))) {
        return new ValuePair<Boolean, ValidationError<String, String>>(false,
            new ValidationError<String, String>("Illegal character found ", value.toString(),
                this.allowedChars.toLowerCase()));
      }
    }
    return new ValuePair<Boolean, ValidationError<String, String>>(true, null);
  }

  @Override
  public final String toString() {
    return "{\"mandatory\":\"" + mandatory
        + "\",\"unique\":\"" + unique
        + "\",\"max\":\"" + max
        + "\",\"min\":\"" + min
        + "\",\"allowedChars\":\"" + allowedChars
        + ",\"subStrings\":\"" + subStringToString()
        + "}";
  }

  private String subStringToString() {
    if (subStrings == null) {
      return "[]";
    }
    StringBuilder sb = new StringBuilder("[");
    subStrings.forEach(e -> {
      sb.append("{\"" + e.key() + "\":\"" + e.value() + "\" },");
    });
    sb.deleteCharAt(sb.length() - 1);
    sb.append("]");
    return sb.toString();
  }

}
