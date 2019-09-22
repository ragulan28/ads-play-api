package com.ragul.adsplayapi.Controller;

import com.ragul.adsplayapi.Model.Company;
import com.ragul.adsplayapi.Model.Game;
import com.ragul.adsplayapi.Model.GameUser;
import com.ragul.adsplayapi.Model.User;
import com.ragul.adsplayapi.Repository.GameUserRepository;
import com.ragul.adsplayapi.Service.CompanyService;
import com.ragul.adsplayapi.Service.FileStorageService;
import com.ragul.adsplayapi.Service.GameService;
import com.ragul.adsplayapi.payload.ApiResponse;
import com.ragul.adsplayapi.util.AdsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    GameService gameService;
    @Autowired
    CompanyService companyService;
    @Autowired
    private GameUserRepository gameUserRepository;

    @PostMapping("")
    public ResponseEntity<ApiResponse<Game>> createGame(
            @RequestParam() MultipartFile file,
            @RequestParam() MultipartFile banner,
            @RequestParam() String name,
            @RequestParam() Long companyId,
            @RequestParam String gameType,
            @RequestParam String description,
            @RequestParam AdsType adsType) {
        System.out.println("Request came");
        Company company = companyService.findById(companyId);
        String fileName = fileStorageService.storeFile(file);
        String bannerName = fileStorageService.storeFile(banner);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/downloadFile/")
                .path(fileName)
                .toUriString();
        String bannerDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/downloadFile/")
                .path(bannerName)
                .toUriString();

        Game game = new Game();
        game.setName(name);
        game.setGameType(gameType);
        game.setImageUrl(fileDownloadUri);
        game.setAdsType(adsType);
        game.setEnable(true);
        game.setDescription(description);

        game.setBannerImageUrl(bannerDownloadUri);

        game.setCompany(company);
        System.out.println(game);
        gameService.save(game);
        return new ResponseEntity<>(new ApiResponse<>(game, HttpStatus.CREATED, "Game create Successfully"), HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Game>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse<>(gameService.findById(id)), HttpStatus.OK);
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<ApiResponse<List<User>>> getUserForGame(@PathVariable Long id) {
        Game game = gameService.findById(id);
        List<GameUser> gameUsers = this.gameUserRepository.findAllByGame(game);
        List<User> users = gameUsers.stream()
                .map(GameUser::getUser)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ApiResponse<>(users), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Game>> update(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse<>(gameService.findById(id)), HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Game>>> findAll() {
        return new ResponseEntity<>(new ApiResponse<>(gameService.findAll()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Company>> deleteById(@PathVariable Long id) {
        gameService.delete(id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK,"Game : "+id+" deleted"), HttpStatus.OK);
    }

}
