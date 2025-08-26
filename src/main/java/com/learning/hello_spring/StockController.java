package com.learning.hello_spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final CachedStockService cachedStockService;

    @Autowired
    public StockController(CachedStockService cachedStockService) {
        this.cachedStockService = cachedStockService;
    }

    @GetMapping("/top")
    public Object getTopStocks() {
        return cachedStockService.getCachedGainers();
    }

    @GetMapping("/losers")
    public Object getTopLosers() {
        return cachedStockService.getCachedLosers();
    }
}
