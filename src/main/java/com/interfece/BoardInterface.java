package com.interfece;

import org.springframework.http.*;
import com.demoData.*;
import com.dto.dto.*;

public interface BoardInterface {
  ResponseEntity<String> create(BoardDto dto);

  ResponseEntity<String> update(BoardDto dto);

  ResponseEntity<String> delete(BoardDto dto);

  ResponseEntity<BoardDto> read(CompanyDto dto);

  ResponseEntity<BoardDto> updateMember(CompanyDto dto);

  ResponseEntity<BoardDto> removeMember(CompanyDto dto);

  boolean validRoleType(Object role);

  // Board convertDtoToDao(BoardDto dto);

  // BoardDto convertDaoToDto(Board dao);
}