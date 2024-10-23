package com.service;

import java.util.*;
import java.util.stream.*;

import org.springframework.http.*;
import org.springframework.stereotype.*;

import com.dao.*;
import com.demoData.*;
import com.dto.dto.*;
import com.interfece.*;
import com.repositories.*;

import lombok.*;

@RequiredArgsConstructor
@Service
public class OwnerService implements OwnerInterface {
  private final OwnerRepository ownerRepository;

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
  public ResponseEntity<OwnersDto> read(CompanyDto dto) {
    Optional<Owners> owner = this.ownerRepository.findByBusinessId(dto.businessId());
    if (owner.isPresent()) {
      return new ResponseEntity<>(convertOwenersToDto(owner.get()), HttpStatus.OK);
    }
    return new ResponseEntity<>(OwnersDto.builder().build(), HttpStatus.OK);

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

  public OwnersDto convertOwenersToDto(Owners dao) {
    return OwnersDto.builder()
        .businessId(dao.getBusinessId())
        .id(dao.getId())
        .owners(dao.getOwners().stream().map(owner -> convertDaoToDto(owner))
            .toList())
        .build();
  }

  @Override
  public ResponseEntity<OwnersDto> updateMember(CompanyDto dto) {
    Optional<Owners> owners = this.ownerRepository.findByBusinessId(dto.businessId());
    if (owners.isEmpty()) {
      this.ownerRepository.save(Owners.builder()
          .businessId(dto.businessId())
          .owners(Arrays.asList(Owner.builder()
              .personId(dto.personId())
              .percentage(dto.percentage())
              .build()))
          .build());
    } else {
      List<Owner> newOwList = owners.get().getOwners().stream()
          .filter(owner -> !owner.getPersonId().equals(dto.personId()))
          .collect(Collectors.toCollection(ArrayList::new));

      if (dto.percentage() != 0) {
        newOwList.add(Owner.builder().percentage(dto.percentage()).personId(dto.personId()).build());
      }

      owners.get().setOwners(newOwList);
      this.ownerRepository.save(owners.get());
    }
    var o = this.ownerRepository.findByBusinessId(dto.businessId());
    return new ResponseEntity<>(convertOwenersToDto(o.get()), HttpStatus.OK);

  }

}
