package com.ragul.adsplayapi.Service;

import com.ragul.adsplayapi.Model.Company;
import com.ragul.adsplayapi.Model.Game;
import com.ragul.adsplayapi.Repository.GameRepository;
import com.ragul.adsplayapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    public void save(Game game) {
        gameRepository.save(game);
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }


    public Game findById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.orElseThrow(() -> new ResourceNotFoundException("Game", id.toString()));
    }

    public List<Game> findByCompany(Company company) {
        List<Game> games = gameRepository.findAllByCompany(company);
        return games;
    }

    public void delete(Long id) {
        Game game = this.findById(id);
        gameRepository.delete(game);
    }

    public void deleteByCompany(Company company) {
        List<Game> games = this.findByCompany(company);
        gameRepository.deleteAll(games);
    }
}
