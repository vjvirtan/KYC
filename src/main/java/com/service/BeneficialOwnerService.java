package com.service;

import java.util.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import com.dao.*;
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
  public ResponseEntity<String> update(BeneficialOwnersDto dto) {
    this.validationInterface.validateFields(dto);
    Optional<BeneficialOwners> dao = this.beneficialOwnersRepository.findById(dto.id());
    if (dao.isPresent()) {
      BeneficialOwners d = dao.get();
      d.setBeneficiaries(dto.beneficiaries().stream().map(e -> convertDtoToDao(e)).toList());
      this.beneficialOwnersRepository.save(d);
    }

    return new ResponseEntity<>(dto.toString(), HttpStatus.OK);

  }

  @Override
  public ResponseEntity<String> delete(BeneficialOwnersDto dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public ResponseEntity<String> read(BeneficialOwnersDto dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'read'");
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

}
