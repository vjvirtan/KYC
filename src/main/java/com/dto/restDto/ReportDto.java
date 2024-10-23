package com.dto.restDto;

import com.dto.dto.*;

import lombok.*;

@Builder
public record ReportDto(String businessId, BoardDto board, OwnersDto owners, BeneficialOwnersDto beneficiaries) {

}
