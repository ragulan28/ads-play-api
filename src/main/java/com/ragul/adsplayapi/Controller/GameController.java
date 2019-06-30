package com.ragul.adsplayapi.Controller;

import com.ragul.adsplayapi.Model.Company;
import com.ragul.adsplayapi.Model.Game;
import com.ragul.adsplayapi.Service.CompanyService;
import com.ragul.adsplayapi.Service.FileStorageService;
import com.ragul.adsplayapi.Service.GameService;
import com.ragul.adsplayapi.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Repository
@RequestMapping("/api/game")
public class GameController {
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    GameService gameService;
    @Autowired
    CompanyService companyService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<Game>> createGame(@RequestParam("file") MultipartFile file, @RequestParam() String name, @RequestParam() Long companyId) {
        Company company = companyService.findById(companyId);
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/downloadFile/")
                .path(fileName)
                .toUriString();

        Game game = new Game();
        game.setName(name);
        game.setImageUrl(fileDownloadUri);
        game.setCompany(company);
        gameService.save(game);

        return new ResponseEntity<>(new ApiResponse<>(game, HttpStatus.CREATED, "Game create Successfully"), HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Game>> findById(@PathVariable Long id) {
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
