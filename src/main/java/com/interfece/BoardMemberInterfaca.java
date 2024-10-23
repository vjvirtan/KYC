package com.interfece;

import org.springframework.http.*;
import com.dao.*;
import com.dto.dto.*;

public interface BoardMemberInterfaca {
  ResponseEntity<String> create(BoardDto dto);

  ResponseEntity<String> update(BoardDto dto);

  ResponseEntity<String> delete(BoardDto dto);

  ResponseEntity<String> read(BoardDto dto);

  ResponseEntity<String> rules(BenefiaryOwnerDto dto);

  BeneficialOwner convertDtoToDao(BenefiaryOwnerDto dto);

  BenefiaryOwnerDto convertDaoToDto(BeneficialOwner dao);

  BoardDto convertBeneficialOwnersDaoTo(BeneficialOwners dao);

  BeneficialOwners convertBoardDtoTo(BoardDto dto);
}