package org.anandalok.newsaggregator.controller;

import org.anandalok.newsaggregator.model.User;
import org.anandalok.newsaggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/preferences")
    public ResponseEntity<String> getPreferences(@RequestParam String username) {
        Optional<User> user = userService.findUserByUsername(username);
        return user.map(value -> ResponseEntity.ok(value.getNewsPreferences()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/preferences")
    public ResponseEntity<User> updatePreferences(@RequestParam String username, @RequestBody String preferences) {
        User updatedUser = userService.updateUserPreferences(username, preferences);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

