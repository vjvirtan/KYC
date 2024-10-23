package com.dto.dto;

import java.util.*;
import com.interfece.*;

import lombok.*;

@Builder
public record OwnersDto(List<OwnerDto> owners, String businessId, String id) implements RuledDtoInterface {
}