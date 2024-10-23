package com.repositories;

import org.springframework.data.mongodb.repository.*;

import com.dao.*;

public interface OwnerRepository extends MongoRepository<Owners, String> {

}
