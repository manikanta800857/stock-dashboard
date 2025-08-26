package com.learning.hello_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final IndianStockApiService indianStockApiService;

    @Autowired
    public StockController(IndianStockApiService indianStockApiService) {
        this.indianStockApiService = indianStockApiService;
    }

    @GetMapping("/top")
    public Object getTopStocks() {
        return indianStockApiService.getTopPerformers();
    }

    @GetMapping("/losers")
    public Object getTopLosers() {
    return indianStockApiService.getTopPerformers(); // You'll need to implement this method
}
}
