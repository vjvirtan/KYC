package com.repositories;

import java.util.*;

import org.springframework.data.mongodb.repository.*;

import com.dao.*;

public interface OwnerRepository extends MongoRepository<Owners, String> {

  Optional<Owners> findByBusinessId(String businessId);

}
