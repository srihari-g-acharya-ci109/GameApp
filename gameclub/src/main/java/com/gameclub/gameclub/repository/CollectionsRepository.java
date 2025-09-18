package com.gameclub.gameclub.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.gameclub.gameclub.model.CollectionsDaily;

@Repository
public interface CollectionsRepository extends MongoRepository<CollectionsDaily, String> {

}
