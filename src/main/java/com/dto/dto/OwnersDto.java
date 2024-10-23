package com.dto.dto;

import java.util.*;

import com.dao.*;
import com.interfece.*;

import lombok.*;

@Builder
public record OwnersDto(List<Owner> owners, String businessId, String id) implements RuledDtoInterface {
}