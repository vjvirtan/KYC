package com.dto.dto;

import com.interfece.*;

import lombok.*;

@Builder
public record OwnerDto(String id, String personId, float percentage) implements RuledDtoInterface {

}
