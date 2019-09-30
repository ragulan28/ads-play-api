package com.ragul.adsplayapi.Controller;

import com.ragul.adsplayapi.Model.Admin;
import com.ragul.adsplayapi.Service.AdminService;
import com.ragul.adsplayapi.payload.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("")
    public ResponseEntity<ApiResponse<Admin>> create(@RequestBody Admin user) {
        this.adminService.save(user);

        return new ResponseEntity<>(new ApiResponse<>(user, HttpStatus.CREATED, "Admin create Successfully"), HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Admin>> login(@RequestParam String email, @RequestParam String password) {
        Admin isValid = this.adminService.login(email, password);

        return new ResponseEntity<>(new ApiResponse<>(isValid, HttpStatus.OK), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Admin>> findById(@PathVariable Long id) {
        return new ResponseEntity<>(new ApiResponse<>(this.adminService.findById(id)), HttpStatus.OK);
    }
}
