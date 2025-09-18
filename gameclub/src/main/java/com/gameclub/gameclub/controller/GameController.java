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
    public Game saveGame(@RequestBody GameDto dto) {
        return gameService.create(dto);
    }

    @GetMapping("/all")
    public List<Game> viewAllGames() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable String id) {
        return gameService.findById(id);
    }

    @PutMapping("/{id}")
    public Game updateGame(@PathVariable String id, @RequestBody GameDto dto) {
        return gameService.update(id, dto);
    }
}
