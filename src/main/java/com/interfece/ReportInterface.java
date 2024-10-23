package com.interfece;

import org.springframework.http.*;

import com.demoData.*;
import com.dto.restDto.*;

public interface ReportInterface {
  ResponseEntity<ReportDto> report(CompanyDto dto);
}