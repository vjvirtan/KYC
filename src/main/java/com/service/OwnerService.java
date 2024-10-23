package com.service;

import org.springframework.http.*;
import org.springframework.stereotype.*;

import com.dao.*;
import com.dto.*;
import com.dto.dto.*;
import com.interfece.*;

@Service
public class OwnerService implements OwnerInterface {

  @Override
  public ResponseEntity<String> create(BoardDto dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public ResponseEntity<String> update(BoardDto dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public ResponseEntity<String> delete(BoardDto dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public ResponseEntity<String> read(BoardDto dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'read'");
  }

  @Override
  public Owner convertDtoToDao(OwnerDto dto) {
    return Owner.builder()
        .personId(dto.personId()).percentage(dto.percentage())
        .build();
  }

  @Override
  public OwnerDto convertDaoToDto(Owner dao) {
    return OwnerDto.builder()
        .id(dao.getId())
        .personId(dao.getPersonId())
        .percentage(dao.getPercentage())
        .build();
  }

}
