package com.gameclub.gameclub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gameclub.gameclub.dto.GameDto;
import com.gameclub.gameclub.model.Game;
import com.gameclub.gameclub.services.GameService;

@RestController
@CrossOrigin("*")
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/save")
    public Game saveGame(@RequestBody GameDto gameDto) {
        Game game = new Game();
        game.setName(gameDto.getName());
        game.setDescription(gameDto.getDescription());
        game.setPrice(gameDto.getPrice());
        return gameService.create(game);
    }

    @GetMapping("/all")
    public List<Game> viewAllGames() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable String id) {
        return gameService.findById(id);
    }
}
