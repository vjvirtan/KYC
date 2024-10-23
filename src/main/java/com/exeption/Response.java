package com.exeption;

import com.exeption.valid.*;

import lombok.*;

@Builder
public record Response(String key, ValidationErrors errorMessage) {
}