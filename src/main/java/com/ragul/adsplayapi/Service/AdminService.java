package com.ragul.adsplayapi.Service;

import com.ragul.adsplayapi.Model.Admin;
import com.ragul.adsplayapi.Repository.AdminRepository;
import com.ragul.adsplayapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public void save(Admin admin) {
        admin.setPassword(hash(admin.getPassword()));
        adminRepository.save(admin);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }


    public Admin findById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.orElseThrow(() -> new ResourceNotFoundException("Admin", id.toString()));
    }

    public void delete(Long id) {
        Admin admin = this.findById(id);
        adminRepository.delete(admin);
    }

    public Admin login(String email, String password) {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent()){
            if (this.verifyHash(password, admin.get().getPassword())) {
                 return admin.get();
             }
        }
        return null;
    }

    private String hash(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            return myHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean verifyHash(String password, String hash) {
        return hash(password).equals(hash);
    }
}
