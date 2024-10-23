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


@Service
@RequiredArgsConstructor
public class BeneficialOwnerService implements BeneficialOwnerInterface {
  private final BeneficialOwnersRepository beneficialOwnersRepository;
  private final ValidationInterface validationInterface;

  @Override
  public ResponseEntity<String> create(BeneficialOwnersDto dto) {
    this.validationInterface.validateFields(dto);
    return new ResponseEntity<String>(dto.toString(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<BeneficialOwnersDto> update(CompanyDto dto) {
    throw new UnsupportedOperationException("Unimplemented method 'delete'");

  }

  @Override
  public ResponseEntity<String> delete(BeneficialOwnersDto dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public ResponseEntity<BeneficialOwnersDto> read(CompanyDto dto) {
    Optional<BeneficialOwners> owners = this.beneficialOwnersRepository.findByBusinessId(dto.businessId());
    if (owners.isPresent()) {
      return new ResponseEntity<>(convertBeneficialOwnersDaoTo(owners.get()), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(BeneficialOwnersDto.builder().build(), HttpStatus.OK);
    }
    // throw new NoSuchElementException();
  }

  @Override
  public ResponseEntity<String> rules(BenefiaryOwnerDto dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'rules'");
  }

  @Override

  public BeneficialOwner convertDtoToDao(BenefiaryOwnerDto dto) {
    return BeneficialOwner.builder()
        .personId(dto.personId())
        .build();
  }

  @Override
  public BenefiaryOwnerDto convertDaoToDto(BeneficialOwner dao) {
    return BenefiaryOwnerDto.builder()
        .personId(dao.getPersonId())
        .build();
  }

  @Override
  public BeneficialOwnersDto convertBeneficialOwnersDaoTo(BeneficialOwners dao) {
    return BeneficialOwnersDto.builder()
        .businessId(dao.getBusinessId())
        .id(dao.getId())
        .beneficiaries(dao.getBeneficiaries().stream().map(d -> convertDaoToDto(d)).toList())
        .build();
  }

  @Override
  public BeneficialOwners convertBeneficialOwnersDtoTo(BeneficialOwnersDto dto) {
    return BeneficialOwners.builder()
        .businessId(dto.businessId())
        .beneficiaries(dto.beneficiaries().stream().map(d -> convertDtoToDao(d)).toList())
        .build();
  }

  @Override
  public ResponseEntity<BeneficialOwnersDto> updateMember(CompanyDto dto) {
    Optional<BeneficialOwners> owners = this.beneficialOwnersRepository.findByBusinessId(dto.businessId());
    if (owners.isEmpty()) {
      this.beneficialOwnersRepository.save(BeneficialOwners.builder()
          .businessId(dto.businessId())
          .beneficiaries(Arrays.asList(BeneficialOwner.builder()
              .personId(dto.personId())

              .build()))
          .build());
    } else {
      List<BeneficialOwner> newOwnerList = owners.get().getBeneficiaries().stream()
          .filter(owner -> !owner.getPersonId().equals(dto.personId()))
          .collect(Collectors.toCollection(ArrayList::new));
      if (dto.addRemove()) {
        newOwnerList.add(BeneficialOwner.builder().personId(dto.personId()).build());
      }
      owners.get().setBeneficiaries(newOwnerList);
      this.beneficialOwnersRepository.save(owners.get());
    }

    return read(dto);
  }

}
