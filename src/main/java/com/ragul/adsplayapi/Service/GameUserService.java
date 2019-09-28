package com.ragul.adsplayapi.Service;

import com.ragul.adsplayapi.Model.Company;
import com.ragul.adsplayapi.Model.Game;
import com.ragul.adsplayapi.Model.GameUser;
import com.ragul.adsplayapi.Model.User;
import com.ragul.adsplayapi.Repository.GameUserRepository;
import com.ragul.adsplayapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameUserService {
    @Autowired
    GameUserRepository gameUserRepository;
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @Autowired
    GameService gameService;

    public void save(GameUser game) {
        gameUserRepository.save(game);
    }

    public List<GameUser> findAll() {
        return gameUserRepository.findAll();
    }


    public GameUser findById(Long id) {
        Optional<GameUser> game = gameUserRepository.findById(id);
        return game.orElseThrow(() -> new ResourceNotFoundException("Game", id.toString()));
    }

    public List<GameUser> findByCompanyId(Long id) {

        Company company = this.companyService.findById(id);
        List<Game> games = this.gameService.findByCompany(company);
        List<GameUser> gameUsers = new ArrayList<>();
        games.forEach(game -> gameUsers.addAll(this.gameUserRepository.findAllByGame(game)));
        return gameUsers;
    }


    public List<GameUser> findByUserId(Long id) {
        User user = this.userService.findById(id);
        return this.gameUserRepository.findAllByUser(user);
    }

    public List<GameUser> findByGameId(Long id) {
        Game game = this.gameService.findById(id);
        List<GameUser> gameUsers = gameUserRepository.findAllByGame(game);
        return gameUsers;
    }
}
