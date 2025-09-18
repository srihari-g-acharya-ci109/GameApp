package com.gameclub.gameclub.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gameclub.gameclub.model.CollectionsDaily;

@Repository
public interface CollectionsRepository extends MongoRepository<CollectionsDaily, String> {
    Optional<CollectionsDaily> findByDate(LocalDate date);
}
