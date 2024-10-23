package com.repositories;

import java.util.*;

import org.springframework.data.mongodb.repository.*;

import com.dao.*;

public interface BoardRepository extends MongoRepository<Board, String> {

  Optional<Board> findByBusinessId(String businessId);


  boolean existsByBusinessId(Object object);

  @Query(value = "{ 'businessId': ?0, 'roles': { $elemMatch: { 'personId': ?1, 'boardRole': ?2 } } }", exists = true)
  boolean existsByBoardRoleAndPersonId(String businessId, String personId, String role);

}
