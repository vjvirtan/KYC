package com.interfece;

import org.springframework.http.*;

import com.dao.*;
import com.demoData.*;
import com.dto.dto.*;

public interface OwnerInterface {
  ResponseEntity<String> create(BoardDto dto);

  ResponseEntity<String> update(BoardDto dto);

  ResponseEntity<String> delete(BoardDto dto);

  ResponseEntity<OwnersDto> read(CompanyDto dto);

  Owner convertDtoToDao(OwnerDto dto);

  OwnerDto convertDaoToDto(Owner dao);

  ResponseEntity<OwnersDto> updateMember(CompanyDto dto);
}
