package com.interfece;

import org.springframework.http.*;

import com.dao.*;
import com.demoData.*;
import com.dto.dto.*;


public interface BeneficialOwnerInterface {
  ResponseEntity<String> create(BeneficialOwnersDto dto);

  ResponseEntity<BeneficialOwnersDto> update(CompanyDto dto);

  ResponseEntity<String> delete(BeneficialOwnersDto dto);

  ResponseEntity<BeneficialOwnersDto> read(CompanyDto dto);

  ResponseEntity<String> rules(BenefiaryOwnerDto dto);

  ResponseEntity<BeneficialOwnersDto> updateMember(CompanyDto dto);

  BeneficialOwner convertDtoToDao(BenefiaryOwnerDto dto);

  BenefiaryOwnerDto convertDaoToDto(BeneficialOwner dao);

  BeneficialOwnersDto convertBeneficialOwnersDaoTo(BeneficialOwners dao);

  BeneficialOwners convertBeneficialOwnersDtoTo(BeneficialOwnersDto dto);

}