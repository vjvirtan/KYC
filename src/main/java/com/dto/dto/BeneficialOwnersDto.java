package com.dto.dto;

import java.util.*;

import com.interfece.*;

import lombok.*;


@Builder
public record BeneficialOwnersDto(String id,
        String businessId,
        List<BenefiaryOwnerDto> beneficiaries)
    implements RuledDtoInterface {

}
