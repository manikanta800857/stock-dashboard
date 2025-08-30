package com.learning.hello_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CachedStockService {

    private final IndianStockApiService stockApiService;

    private final AtomicReference<Object> cachedstocks = new AtomicReference<>();

    @Autowired
    public CachedStockService(IndianStockApiService stockApiService) {
        this.stockApiService = stockApiService;
    }

    // Runs every 12 hours (43,200,000 ms)
    @Scheduled(fixedRate = 43200000)
    public void refreshCache() {
        cachedstocks.set(stockApiService.getTopPerformers());
    }

    public Object getCachedGainers() {
        return cachedstocks.get();
    }
    public Object getCachedLosers() {
        return cachedstocks.get();
    }
}
