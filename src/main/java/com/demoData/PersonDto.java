package com.demoData;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PersonDto(String id, String personId, String firstName, String lastName, Set<Locale> nationalities) {
}
