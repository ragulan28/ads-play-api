package com.ragul.adsplayapi.Controller;

import com.ragul.adsplayapi.Model.Company;
import com.ragul.adsplayapi.Model.Game;
import com.ragul.adsplayapi.Service.CompanyService;
import com.ragul.adsplayapi.Service.GameService;
import com.ragul.adsplayapi.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    GameService gameService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<Company>> uploadFile(@RequestBody Company company) {
        this.companyService.save(company);

        return new ResponseEntity<>(new ApiResponse<>(company, HttpStatus.CREATED, "Company create successfully"), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Company>> findById(@PathVariable Long id) {
        Company company = companyService.findById(id);
        return new ResponseEntity<>(new ApiResponse<>(company), HttpStatus.OK);
    }

    @GetMapping("/{id}/game")
    public ResponseEntity<ApiResponse<List<Game>>> findGameByComapnyId(@PathVariable Long id) {
        Company company = companyService.findById(id);
        List<Game> games = this.gameService.findByCompany(company);
        return new ResponseEntity<>(new ApiResponse<>(games), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Company>>> findAll() {
        return new ResponseEntity<>(new ApiResponse<>(companyService.findAll()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Company>> deleteById(@PathVariable Long id) {
        companyService.delete(id);
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK,"Company : "+id+" deleted"), HttpStatus.OK);
    }

}
