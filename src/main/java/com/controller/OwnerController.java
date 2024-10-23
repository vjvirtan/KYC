package com.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;

import com.annotation.*;
import com.demoData.*;
import com.dto.dto.*;
import com.interfece.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("owner")
public class OwnerController {
  private final OwnerInterface ownerInterface;

  @Validation
  @PostMapping("update")
  public ResponseEntity<OwnersDto> postMethodName(@RequestBody CompanyDto dto) {

    return this.ownerInterface.updateMember(dto);
  }

}
