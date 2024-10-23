package com.service;

import java.util.*;
import java.util.stream.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;
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
  // TODO::
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
  public ResponseEntity<BoardDto> read(CompanyDto dto) {
    Optional<Board> board = this.boardRepository.findByBusinessId(dto.businessId());
    if (board.isPresent()) {
      return new ResponseEntity<>(BoardDto.builder()
          .businessId(board.get().getBusinessId())
          .id(board.get().getId())
          .boardRoles(this.boardRolesService.convertBoardMembersToDto(board.get().getRoles()))
          .build(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(BoardDto.builder().build(), HttpStatus.OK);
    }

  }

  // !!!!!!!!!!!!!!!!!!!! UPDATE BOARD !!!!!!!!!!!!!!!!!!!!!!
  @Override
  public ResponseEntity<BoardDto> updateMember(CompanyDto dto) {
    if (!validRoleType(dto.boardRole())) {
      return new ResponseEntity<>(BoardDto.builder().build(), HttpStatus.NOT_ACCEPTABLE);
    }

    Optional<Board> board = this.boardRepository.findByBusinessId(dto.businessId());

    if (dto.addRemove()) {
      if (board.isEmpty()) {
        ArrayList<BoardRoleDto> roles = new ArrayList<>();
        roles.add(BoardRoleDto.builder().personId(dto.personId()).boardRole(dto.boardRole()).build());
        create(BoardDto.builder()
            .businessId(dto.businessId())
            .boardRoles(roles)
            .build());
      } else {
        // TODO:IF ALLREADY EXISTS, SOME ERROR HAS HAPPENED

        if (!this.boardRepository.existsByBoardRoleAndPersonId(dto.businessId(), dto.personId(), dto.boardRole())) {
          board.get().getRoles().add(BoardRole.builder().personId(dto.personId()).boardRole(dto.boardRole()).build());
          this.boardRepository.save(board.get());
        } else {
          System.out.println(" IF ROLE AND PERSON ID MACH HERE; ");
        }
      }

    } else {

      var b = this.boardRepository.findByBusinessId(dto.businessId());

      if (b.isPresent()) {
        ArrayList<BoardRole> updatedRoles = new ArrayList<>();
        updatedRoles = b.get().getRoles().stream().filter(f -> !f.getId().equals(dto.id()))
            .collect(Collectors.toCollection(ArrayList::new));
        b.get().setRoles(updatedRoles);
        this.boardRepository.save(b.get());
      }
    }

    board = this.boardRepository.findByBusinessId(dto.businessId());
    return new ResponseEntity<>(BoardDto.builder()
        .businessId(dto.businessId())
        .boardRoles(this.boardRolesService.convertBoardMembersToDto(board.get().getRoles()))
        .build(), HttpStatus.OK);
  }

  // TODO: PUT END DATE FOR ROLE, NOT REMOVE
  @Override
  public ResponseEntity<BoardDto> removeMember(CompanyDto dto) {
    Optional<Board> board = this.boardRepository.findByBusinessId(dto.businessId());
    if (board.isEmpty()) {
      return new ResponseEntity<>(BoardDto.builder().build(),
          HttpStatus.BAD_REQUEST);
    }
    Board dao = board.get();
    dao.setRoles(dao.getRoles().stream().filter(role -> !role.getPersonId().equals(dto.personId()))
        .collect(Collectors.toCollection(ArrayList::new)));
    this.boardRepository.save(dao);
    return new ResponseEntity<>(BoardDto.builder()
        .businessId(dto.businessId())
        .boardRoles(this.boardRolesService.convertBoardMembersToDto(board.get().getRoles()))
        .build(), HttpStatus.OK);
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

      if (e.getValue().toLowerCase().equals(role.toLowerCase())) {
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
