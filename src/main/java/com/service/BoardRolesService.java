package com.service;

import java.util.*;
import java.util.stream.*;

import org.springframework.stereotype.*;

import com.dao.*;
import com.dto.dto.*;

@Service
public class BoardRolesService {

  public BoardRole convertDtoToDao(BoardRoleDto dto) {
    return BoardRole.builder()
        .personId(dto.personId())
        .boardRole(dto.boardRole())
        .build();
  }

  public BoardRoleDto convertDaoToDto(BoardRole dao) {

    return BoardRoleDto.builder()
        .personId(dao.getPersonId())
        .boardRole(dao.getBoardRole())
        .id(dao.getId())
        .build();
  }

  public ArrayList<BoardRole> convertBoardMembersToDao(ArrayList<BoardRoleDto> dtos) {
    return dtos.stream().map(dto -> convertDtoToDao(dto))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  public ArrayList<BoardRoleDto> convertBoardMembersToDto(ArrayList<BoardRole> daos) {
    return daos.stream().map(dao -> convertDaoToDto(dao))
        .collect(Collectors.toCollection(ArrayList::new));
  }
}
