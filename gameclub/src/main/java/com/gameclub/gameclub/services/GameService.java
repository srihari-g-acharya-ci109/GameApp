package com.gameclub.gameclub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gameclub.gameclub.dto.GameDto;
import com.gameclub.gameclub.model.Game;
import com.gameclub.gameclub.repository.GameRepository;
import com.gameclub.gameclub.exceptions.IdNotPresentException;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepo;

    public Game create(GameDto dto) {
        Game game = new Game();
        game.setName(dto.getName());
        game.setDescription(dto.getDescription());
        game.setPrice(dto.getPrice());
        game.setId(null);
        return gameRepo.save(game);
    }

    public List<Game> findAll() {
        return gameRepo.findAll();
    }

    public Game findById(String id) {
        return gameRepo.findById(id)
                .orElseThrow(() -> new IdNotPresentException("Game not found: " + id));
    }

    public Game update(String id, GameDto dto) {
        Game existing = findById(id);
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        return gameRepo.save(existing);
    }
}
