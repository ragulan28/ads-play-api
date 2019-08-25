package com.ragul.adsplayapi.Service;

import com.ragul.adsplayapi.Model.User;
import com.ragul.adsplayapi.Repository.UserRepository;
import com.ragul.adsplayapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void save(User user) {
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
}
