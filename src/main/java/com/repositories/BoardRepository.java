package com.repositories;

import java.util.*;

import org.springframework.data.mongodb.repository.*;

import com.dao.*;

public interface BoardRepository extends MongoRepository<Board, String> {

  Optional<Board> findByBusinessId(String businessId);

  boolean existsByBusinessId(Object object);

}
