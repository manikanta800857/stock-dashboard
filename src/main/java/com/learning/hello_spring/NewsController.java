package com.learning.hello_spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final CachedNewsService cachedNewsService;

    public NewsController(CachedNewsService cachedNewsService) {
        this.cachedNewsService = cachedNewsService;
    }

    @GetMapping("")
    public Object getNews() {
        return cachedNewsService.getCachedNews();
    }
}
