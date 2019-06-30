package com.ragul.adsplayapi.Controller;

import com.ragul.adsplayapi.Model.Company;
import com.ragul.adsplayapi.Service.CompanyService;
import com.ragul.adsplayapi.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Repository
@RequestMapping("/api/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;

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
