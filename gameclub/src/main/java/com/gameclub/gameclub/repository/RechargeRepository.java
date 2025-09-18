package com.gameclub.gameclub.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.gameclub.gameclub.model.Recharge;

@Repository
public interface RechargeRepository extends MongoRepository<Recharge, String> {

}
