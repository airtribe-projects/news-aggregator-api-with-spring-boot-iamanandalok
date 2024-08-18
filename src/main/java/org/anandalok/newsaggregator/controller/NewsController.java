package org.anandalok.newsaggregator.controller;


import org.anandalok.newsaggregator.service.NewsService;
import org.anandalok.newsaggregator.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private UserService userService;

    @GetMapping("/news")
    public ResponseEntity<String> getNews(@RequestParam String username) {
        Optional<String> preferences = userService.findUserByUsername(username)
                .map(user -> user.getNewsPreferences());
        if (preferences.isPresent()) {
            return ResponseEntity.ok(newsService.getNewsByCategory(preferences.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

