package org.anandalok.newsaggregator.service;



import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Service
public class NewsService {

    private static final Logger logger = Logger.getLogger(NewsService.class.getName());

    @Value("${newsapi.apiKey}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String getNewsByCategory(String category) {
        String url = "https://newsapi.ai/api/v1/article/getArticles?category=" + category + "&apiKey=" + apiKey;
        logger.info("Fetching news for category: " + category);
        return restTemplate.getForObject(url, String.class);
    }
}

