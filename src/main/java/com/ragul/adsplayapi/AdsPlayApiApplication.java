package com.ragul.adsplayapi;

import com.ragul.adsplayapi.Model.Admin;
import com.ragul.adsplayapi.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdsPlayApiApplication implements CommandLineRunner {
    @Autowired
    AdminRepository adminRepository;

    public static void main(String[] args) {
        SpringApplication.run(AdsPlayApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (!adminRepository.findByEmail("ragulan28@gmail.com").isPresent()) {
            Admin admin = new Admin();
            admin.setName("ragulan");
            admin.setEmail("ragulan28@gmail.com");
            admin.setPassword("ragulan28@gmail.com");
            adminRepository.save(admin);
        }
    }
}
