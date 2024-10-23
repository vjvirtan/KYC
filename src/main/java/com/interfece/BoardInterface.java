package com.interfece;

import java.io.*;

import org.springframework.http.*;

import com.controller.exeption.*;
import com.demoData.*;
import com.dto.dto.*;

public interface BoardInterface {
  ResponseEntity<String> create(BoardDto dto);

  ResponseEntity<String> update(BoardDto dto);

  ResponseEntity<String> delete(BoardDto dto);

  ResponseEntity<String> read(BoardDto dto);

  ResponseEntity<ResponseTemplate> updateMember(CompanyDto dto);

  ResponseEntity<ResponseTemplate> removeMember(CompanyDto dto);

  boolean validRoleType(Object role);

  // Board convertDtoToDao(BoardDto dto);

  // BoardDto convertDaoToDto(Board dao);
}