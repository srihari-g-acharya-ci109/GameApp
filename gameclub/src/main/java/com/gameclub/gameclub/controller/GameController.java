package com.gameclub.gameclub.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gameclub.gameclub.model.Game;
import com.gameclub.gameclub.repository.GameRepository;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path="/games")
public class GameController {
    @Autowired
    private GameRepository repo;

    @PostMapping
    public Game create(@RequestBody Game game){
        game.setId(null);
        Game savedGame=repo.save(game);
        return savedGame;
    }

    @GetMapping
    public List<Game>findAll(){
        List<Game> games=repo.findAll();
        return games;
    }

    @GetMapping(path="/{id}")
    public Game findById(@PathVariable String id){
        Game game=repo.findById(id).get();
        return game;
    }


    @PutMapping(path="/{id}")
    public Game update(@PathVariable String id,@RequestBody Game game){
        Game oldGame=repo.findById(id).get();
        oldGame.setName(game.getName());
        oldGame.setDescription(game.getDescription());
        oldGame.setPrice(game.getPrice());

        Game updatedGame=repo.save(oldGame);
        return updatedGame;
    }


    @DeleteMapping(path="/{id}")
    public void delete(@PathVariable String id){
        repo.deleteById(id);
    }       
}   
    

