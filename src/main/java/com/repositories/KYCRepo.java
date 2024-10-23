package com.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.dao.KYC;

public interface KYCRepo extends MongoRepository<KYC, String> {

}
