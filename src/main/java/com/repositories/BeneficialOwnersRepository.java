package com.repositories;

import java.util.*;

import org.springframework.data.mongodb.repository.*;

import com.dao.*;

public interface BeneficialOwnersRepository extends MongoRepository<BeneficialOwners, String> {

  Optional<BeneficialOwners> findByBusinessId(String businessId);

}
