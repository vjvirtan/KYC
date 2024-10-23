package com.interfece;

import org.springframework.http.*;
import com.dao.*;
import com.dto.dto.*;

public interface BeneficialOwnerInterface {
  ResponseEntity<String> create(BeneficialOwnersDto dto);

  ResponseEntity<String> update(BeneficialOwnersDto dto);

  ResponseEntity<String> delete(BeneficialOwnersDto dto);

  ResponseEntity<String> read(BeneficialOwnersDto dto);

  ResponseEntity<String> rules(BenefiaryOwnerDto dto);

  BeneficialOwner convertDtoToDao(BenefiaryOwnerDto dto);

  BenefiaryOwnerDto convertDaoToDto(BeneficialOwner dao);

  BeneficialOwnersDto convertBeneficialOwnersDaoTo(BeneficialOwners dao);

  BeneficialOwners convertBeneficialOwnersDtoTo(BeneficialOwnersDto dto);
}