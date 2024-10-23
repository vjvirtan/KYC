package com.service;

import java.io.*;
import java.util.*;
import java.util.stream.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;

import com.controller.exeption.*;
import com.dao.*;
import com.demoData.*;
import com.dto.dto.*;
import com.enums.*;
import com.interfece.*;
import com.repositories.*;
import lombok.*;

@RequiredArgsConstructor
@Service
public class BoardService implements BoardInterface {

  private final ValidationInterface validationInterface;
  private final BoardRepository boardRepository;
  private final BoardRolesService boardRolesService;

  // TODO: ENSURE INSTANCES ARE REALY UPDATED TO DATABASE, FIX FIX FIX!!!
  @Override
  public ResponseEntity<String> create(BoardDto dto) {
    if (this.boardRepository.existsByBusinessId(dto.businessId())) {
      return new ResponseEntity<>(dto.businessId() + " all ready exists boardRoles database, update only possible!!!",
          HttpStatus.BAD_REQUEST);
    }

    // TODO: VALIDATION !!!!
      this.boardRepository.save(Board.builder()
          .businessId(dto.businessId())
          .roles(boardRolesService.convertBoardMembersToDao(dto.boardRoles())).build());

    return new ResponseEntity<String>(dto.toString(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<String> update(BoardDto dto) {
    Optional<Board> board = this.boardRepository.findById(dto.id());
    if (board.isPresent()) {
      Board dao = board.get();
      dao.setRoles(boardRolesService.convertBoardMembersToDao(dto.boardRoles()));
      this.boardRepository.save(dao);
      return new ResponseEntity<String>(dto.toString(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Updating company " + dto.businessId() + " board  not possible!",
          HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public ResponseEntity<String> delete(BoardDto dto) {
    Optional<Board> board = this.boardRepository.findByBusinessId(dto.businessId());
    if (board.isPresent()) {
      this.boardRepository.delete(board.get());
      // TODO: MAYBY CREATE STANDARD RESPONSE ENTITY BODY
      return new ResponseEntity<>(dto.toString(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Delete operation faild for " + dto.businessId(), HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public ResponseEntity<String> read(BoardDto dto) {
    Optional<Board> board = this.boardRepository.findByBusinessId(dto.businessId());
    if (board.isPresent()) {
      Board dao = board.get();
      return new ResponseEntity<>(BoardDto.builder()
          .businessId(dao.getBusinessId())
          .boardRoles(this.boardRolesService.convertBoardMembersToDto(dao.getRoles()))
          .build().toString(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(dto.businessId() + " have no board members. ", HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<ResponseTemplate> updateMember(CompanyDto dto) {
    if (!validRoleType(dto.info())) {
      return new ResponseEntity<>(ResponseTemplate.builder().valid(false).message("ROLE NAMING INVALID").build(),
          HttpStatus.NOT_ACCEPTABLE);
    }

    Optional<Board> board = this.boardRepository.findByBusinessId(dto.businessId());

    if (board.isEmpty()) {

      create(BoardDto.builder().businessId(dto.businessId()).boardRoles(
          dto.info().stream().map(l -> BoardRoleDto.builder().personId(dto.personId()).boardRole(l).build())
              .collect(Collectors.toCollection(ArrayList::new)))
          .build());
    } else {
      Board dao = board.get();
      ArrayList<BoardRole> replaseRoles = new ArrayList<>();
      // REMOVE PERSON FIRST
      for (BoardRole br : dao.getRoles()) {
        if (!br.getPersonId().equals(dto.personId())) {
          replaseRoles.add(br);
        }
      }
      // THEN ADD PERSON ROLES
      dto.info().forEach(e -> replaseRoles.add(BoardRole.builder().personId(dto.personId()).role(e).build()));
      dao.setRoles(replaseRoles);
      this.boardRepository.save(dao);

    }

    return new ResponseEntity<>(ResponseTemplate.builder()
        .message(read(BoardDto.builder().businessId(dto.businessId()).build()).toString())
        .valid(true)
        .build(), HttpStatus.OK);
  }

  // TODO: PUT END DATE FOR ROLE, NOT REMOVE
  @Override
  public ResponseEntity<ResponseTemplate> removeMember(CompanyDto dto) {
    Optional<Board> board = this.boardRepository.findByBusinessId(dto.businessId());
    if (board.isEmpty()) {
      return new ResponseEntity<>(ResponseTemplate.builder().message("No board for " + dto.businessId()).build(),
          HttpStatus.BAD_REQUEST);
    }
    Board dao = board.get();
    dao.setRoles(dao.getRoles().stream().filter(role -> {
      if (!role.getPersonId().equals(dto.personId())) {
        return true;
      } else {
        for (String info : dto.info()) {
          return !info.equals(role.getRole());
        }
      }
      return true;
    }).collect(Collectors.toCollection(ArrayList::new)));

    return new ResponseEntity<>(ResponseTemplate.builder().valid(true).message(" these are mess :D").build(),
        HttpStatus.BAD_REQUEST);
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean validRoleType(Object roleOrRoles) {
    if (roleOrRoles instanceof String) {
      if (!testRole(roleOrRoles.toString())) {
        return false;
      }
    } else if (roleOrRoles instanceof ArrayList) {
      ArrayList<Object> lista = (ArrayList<Object>) roleOrRoles;
      for (int i = 0; i < lista.size(); i++) {
        System.out.println(lista.get(i).toString());
        if (!testRole(lista.get(i).toString())) {
          return false;
        }
      }
    } else {
      return false;
    }

    return true;
  }

  private boolean testRole(String role) {
    for (BoardRolesEnum e : BoardRolesEnum.values()) {
      System.out.println("testing " + e.getValue().toLowerCase() + "  " + role.toLowerCase());
      if (e.getValue().equals(role)) {
        return true;
      }
    }
    return false;
  }

  /*
   * public Map<String, List<BoardRolesDto>> convertBoardRoles(Map<String,
   * List<BoardRoles>> dao) {
   * return dao.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
   * e -> e.getValue().stream().map(v ->
   * this.boardRolesService.convertDaoToDto(v)).collect(Collectors.toList())));
   * }
   * 
   * public Map<String, List<BoardRoles>> convertBoardDtoRoles(Map<String,
   * List<BoardRolesDto>> dto) {
   * return dto.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
   * e -> e.getValue().stream().map(v ->
   * this.boardRolesService.convertDtoToDao(v)).collect(Collectors.toList())));
   * }
   */
}
