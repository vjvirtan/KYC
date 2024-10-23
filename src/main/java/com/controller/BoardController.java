package com.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.annotation.*;
import com.demoData.*;
import com.dto.dto.*;
import com.interfece.*;
import lombok.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {
  private final BoardInterface boardInterface;

  @Validation
  @PostMapping("update")
  public ResponseEntity<BoardDto> updateRoles(@RequestBody CompanyDto dto) {
    return this.boardInterface.updateMember(dto);
  }

  @Validation
  @PostMapping("remove")
  public ResponseEntity<BoardDto> removeMember(@RequestBody CompanyDto dto) {
    return this.boardInterface.removeMember(dto);
  }

  @Validation
  @PostMapping("board")
  public ResponseEntity<BoardDto> getMembers(@RequestBody CompanyDto dto) {
    return this.boardInterface.read(dto);
  }

}
