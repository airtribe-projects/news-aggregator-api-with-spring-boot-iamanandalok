package org.anandalok.newsaggregator.service;

import org.anandalok.newsaggregator.model.User;
import org.anandalok.newsaggregator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        // Simple example: You might want to add validation here
        return userRepository.save(user);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUserPreferences(String username, String preferences) {
        Optional<User> userOptional = findUserByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNewsPreferences(preferences);
            return userRepository.save(user);
        }
        return null;
    }
}
