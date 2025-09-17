package com.gameclub.gameclub.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.gameclub.gameclub.model.Game;


@Repository
public interface GameRepository extends MongoRepository<Game,String> {


}


    

