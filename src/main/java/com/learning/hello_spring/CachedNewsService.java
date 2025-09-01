package com.learning.hello_spring;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CachedNewsService {

    private final IndianNewsApiService indianNewsApiService;
    private final AtomicReference<Object> cachedNews = new AtomicReference<>();

    public CachedNewsService(IndianNewsApiService indianNewsApiService) {
        this.indianNewsApiService = indianNewsApiService;
    }

    // Update news every 12 hours
    @Scheduled(fixedRate = 43200000)
    public void refreshCache() {
        cachedNews.set(indianNewsApiService.getNews());
    }

    public Object getCachedNews() {
        return cachedNews.get();
    }
}
