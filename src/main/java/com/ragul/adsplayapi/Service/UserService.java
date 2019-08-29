package com.ragul.adsplayapi.Service;

import com.ragul.adsplayapi.Model.User;
import com.ragul.adsplayapi.Repository.UserRepository;
import com.ragul.adsplayapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void save(User user) {
        user.setPassword(hash(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException("User", id.toString()));
    }

    public void delete(Long id) {
        User user = this.findById(id);
        userRepository.delete(user);
    }

    public User login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()){
            if (this.verifyHash(password, user.get().getPassword())) {
                 return user.get();
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
