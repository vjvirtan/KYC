package com.dto.dto;

import com.interfece.*;

import lombok.*;

@Builder
public record BenefiaryOwnerDto(String id, String personId) implements RuledDtoInterface {

}
