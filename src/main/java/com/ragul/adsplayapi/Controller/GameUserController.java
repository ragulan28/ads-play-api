package com.ragul.adsplayapi.Controller;

import com.ragul.adsplayapi.Model.Game;
import com.ragul.adsplayapi.Model.GameUser;
import com.ragul.adsplayapi.Model.User;
import com.ragul.adsplayapi.Service.GameService;
import com.ragul.adsplayapi.Service.GameUserService;
import com.ragul.adsplayapi.Service.UserService;
import com.ragul.adsplayapi.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/play")
public class GameUserController {
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;
    @Autowired
    private GameUserService gameUserService;


    @PostMapping("")
    public ResponseEntity<ApiResponse<GameUser>> playGameByUser(@RequestParam Long userId, @RequestParam Long gameId, @RequestParam int score) {
        Game game = gameService.findById(gameId);
        User user = userService.findById(userId);
        GameUser gameUser = new GameUser();
        game.setPlayCont(game.getPlayCont() + 1);
        if (game.getHighScore() < score) {
            game.setHighScore(score);
        }
        gameUser.setGame(game);
        gameUser.setUser(user);
        gameUser.setScore(score);
        gameUserService.save(gameUser);

        gameService.save(game);
        return new ResponseEntity<>(new ApiResponse<>(gameUser, HttpStatus.CREATED, "Game play create Successfully"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GameUser>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse<>(gameUserService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<List<GameUser>>> findByUserId(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse<>(gameUserService.findByUserId(id)), HttpStatus.OK);
    }
    @GetMapping("/company/{id}")
    public ResponseEntity<ApiResponse<List<GameUser>>> findByCompanyId(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse<>(gameUserService.findBCompanyId(id)), HttpStatus.OK);
    }

}
