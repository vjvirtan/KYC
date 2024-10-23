package com.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import lombok.*;

import com.annotation.*;
import com.demoData.*;
import com.dto.restDto.*;
import com.interfece.*;

@RestController
@RequestMapping("report")
@RequiredArgsConstructor
public class ReportController {
  private final ReportInterface reportInterface;

  @Validation
  @PostMapping("/get")
  public ResponseEntity<ReportDto> reportKyc(@RequestBody CompanyDto dto) {
    return this.reportInterface.report(dto);
  }

}
