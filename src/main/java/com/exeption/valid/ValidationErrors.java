package com.exeption.valid;

import java.util.*;

public record ValidationErrors(List<ValidationError<String, String>> errors) {

}
