package com.ragul.adsplayapi.Controller;

import com.ragul.adsplayapi.Model.Game;
import com.ragul.adsplayapi.Model.GameUser;
import com.ragul.adsplayapi.Model.User;
import com.ragul.adsplayapi.Repository.GameUserRepository;
import com.ragul.adsplayapi.Service.GameService;
import com.ragul.adsplayapi.Service.UserService;
import com.ragul.adsplayapi.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;
    @Autowired
    private GameUserRepository gameUserRepository;

    @PostMapping("")
    public ResponseEntity<ApiResponse<User>> playGame(@RequestBody User user) {
        this.userService.save(user);

        return new ResponseEntity<>(new ApiResponse<>(user, HttpStatus.CREATED, "Game create Successfully"), HttpStatus.OK);

    }
    @GetMapping("/{id}/game")
    public ResponseEntity<ApiResponse<List<Game>>> getUserForGame(@PathVariable Long id) {
        User user = userService.findById(id);
        List<GameUser> gameUsers = this.gameUserRepository.findAllByUser(user);
        List<Game> games = gameUsers.stream()
                .map(GameUser::getGame)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ApiResponse<>(games), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse<>(this.userService.findById(id)), HttpStatus.OK);
    }
}
