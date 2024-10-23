package com.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.controller.exeption.*;
import com.demoData.*;
import com.dto.dto.*;
import com.interfece.*;

import lombok.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {
  private final BoardInterface boardInterface;

  @PostMapping("update")
  public ResponseEntity<ResponseTemplate> updateRoles(@RequestBody CompanyDto dto) {
    return this.boardInterface.updateMember(dto);
  }

  @PostMapping("remove")
  public ResponseEntity<ResponseTemplate> removeMember(@RequestBody CompanyDto dto) {
    return this.boardInterface.removeMember(dto);
  }

  @PostMapping("board")
  public ResponseEntity<String> getMembers(@RequestBody CompanyDto dto) {
    return this.boardInterface.read(BoardDto.builder().businessId(dto.businessId()).id(dto.id()).build());
  }

}
