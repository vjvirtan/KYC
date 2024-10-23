package com.service;

import org.springframework.http.*;
import org.springframework.stereotype.*;

import com.demoData.*;
import com.dto.dto.*;
import com.dto.restDto.*;
import com.interfece.*;

import lombok.*;

@Service
@RequiredArgsConstructor
public class ReportService implements ReportInterface {
  private final BoardInterface boardInterface;
  private final OwnerInterface ownerInterface;
  private final BeneficialOwnerInterface beneficialOwnerInterface;

  @Override
  public ResponseEntity<ReportDto> report(CompanyDto dto) {
    BoardDto board = this.boardInterface.read(dto).getBody();
    BeneficialOwnersDto bene = this.beneficialOwnerInterface.read(dto).getBody();
    OwnersDto owners = this.ownerInterface.read(dto).getBody();

    return new ResponseEntity<>(ReportDto.builder()
        .businessId(dto.businessId())
        .beneficiaries(bene)
        .board(board)
        .owners(owners)
        .build(), HttpStatus.OK);

  }

}
