package com.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.annotation.*;
import com.demoData.*;
import com.demoData.demoData.*;
import lombok.*;


@RequestMapping("/demodata")
@RestController
@RequiredArgsConstructor
public class DemoDataController {
  private final Demointerface demointerface;

  @Validation
  @PostMapping("/person")
  public ResponseEntity<DemoPerson> personData(@RequestBody PersonDto personDto) {

    DemoPerson dm = this.demointerface.findPerson(personDto.personId());
    return new ResponseEntity<>(dm, HttpStatus.OK);
  }

  @Validation
  @PostMapping("/company")
  public ResponseEntity<DemoCompany> companyData(@RequestBody CompanyDto companyDto) {
    DemoCompany dc = this.demointerface.findCompany(companyDto.businessId());
    return new ResponseEntity<>(dc, HttpStatus.OK);
  }

}
