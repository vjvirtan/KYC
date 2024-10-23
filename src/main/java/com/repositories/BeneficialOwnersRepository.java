package com.repositories;

import org.springframework.data.mongodb.repository.*;

import com.dao.*;

public interface BeneficialOwnersRepository extends MongoRepository<BeneficialOwners, String> {

}
