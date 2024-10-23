package com.dao;

import java.util.*;
import org.springframework.data.mongodb.core.mapping.*;
import lombok.*;

@Document
@Builder
@Getter
@Setter
public class BeneficialOwners extends UUIDGen {
  private String businessId;
  private List<BeneficialOwner> beneficiaries;
}
