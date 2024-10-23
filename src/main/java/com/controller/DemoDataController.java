package com.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.demoData.*;
import com.demoData.demoData.*;

import lombok.*;

@CrossOrigin
@RequestMapping("/demodata")
@RestController
@RequiredArgsConstructor
public class DemoDataController {
  private final Demointerface demointerface;

  @PostMapping("/person")
  public ResponseEntity<DemoPerson> personData(@RequestBody PersonDto personDto) {
    System.out.println(" person data pyyntö " + personDto.toString());
    DemoPerson dm = this.demointerface.findPerson(personDto.personId());
    return new ResponseEntity<>(dm, HttpStatus.OK);
  }

  @PostMapping("/company")
  public ResponseEntity<DemoCompany> companyData(@RequestBody CompanyDto companyDto) {
    System.out.println("" + companyDto.businessId());
    DemoCompany dc = this.demointerface.findCompany(companyDto.businessId());
    return new ResponseEntity<>(dc, HttpStatus.OK);
  }

}
