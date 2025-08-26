package com.learning.hello_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CachedStockService {

    private final IndianStockApiService stockApiService;

    private final AtomicReference<Object> cachedGainers = new AtomicReference<>();
    private final AtomicReference<Object> cachedLosers = new AtomicReference<>();

    @Autowired
    public CachedStockService(IndianStockApiService stockApiService) {
        this.stockApiService = stockApiService;
        refreshCache(); // Fetch once at startup
    }

    // Runs every 12 hours (43,200,000 ms)
    @Scheduled(fixedRate = 43200000)
    public void refreshCache() {
        cachedGainers.set(stockApiService.getTopPerformers());
        cachedLosers.set(stockApiService.getTopLosers());
    }

    public Object getCachedGainers() {
        return cachedGainers.get();
    }
    public Object getCachedLosers() {
        return cachedLosers.get();
    }
}
