package com.learning.hello_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final CachedStockService cachedStockService;

    // Inject CachedNewsService here
    private final CachedNewsService cachedNewsService;

    @Autowired
    public DashboardController(CachedStockService cachedStockService, CachedNewsService cachedNewsService) {
        this.cachedStockService = cachedStockService;
        this.cachedNewsService = cachedNewsService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Your API probably returns a Map with keys "top_gainers" and "top_losers"
        Object gainersObj = cachedStockService.getCachedGainers();
        Object losersObj = cachedStockService.getCachedLosers();

        // Cast to Map to extract arrays
        java.util.Map<String, Object> gainersMap = (java.util.Map<String, Object>) gainersObj;
        java.util.Map<String, Object> losersMap = (java.util.Map<String, Object>) losersObj;

        model.addAttribute("gainers", gainersMap.get("top_gainers"));
        model.addAttribute("losers", losersMap.get("top_losers"));

        // Add news data to model
        Object newsObj = cachedNewsService.getCachedNews();
        model.addAttribute("newsList", newsObj);

        return "dashboard";
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/dashboard";
    }
}
