package com.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.annotation.*;
import com.demoData.*;
import com.dto.dto.*;
import com.interfece.*;

import lombok.*;

@RestController
@RequestMapping("beneficiary")
@RequiredArgsConstructor
public class BeneficiaryController {
  private final BeneficialOwnerInterface beneficialOwnerInterface;

  @Validation
  @PostMapping("update")
  public ResponseEntity<BeneficialOwnersDto> postMethodName(@RequestBody CompanyDto dto) {

    return this.beneficialOwnerInterface.updateMember(dto);
  }

}
