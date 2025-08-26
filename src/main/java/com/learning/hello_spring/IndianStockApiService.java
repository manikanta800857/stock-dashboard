package com.learning.hello_spring;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

@Service
public class IndianStockApiService {

    @Value("${indianapi.base-url}")
    private String baseUrl;

    @Value("${indianapi.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public IndianStockApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object getTopPerformers() {
        try {
            String url = baseUrl + "/trending";

            HttpHeaders headers = new HttpHeaders();
           headers.set("x-api-key", apiKey);
            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    Map.class
            );

            System.out.println("RAW API RESPONSE: " + response.getBody());

            // Change "trending_stocks" to the correct field name as per your API JSON
            return response.getBody() != null ? response.getBody().get("trending_stocks") : Collections.emptyList();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptyList();
        }
    }

        public Object getTopLosers() {
    try {
        String url = baseUrl + "/trending";  // Use same endpoint for now
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-api-key", apiKey);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        
        ResponseEntity<Map> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            Map.class
        );
        
        System.out.println("LOSERS API RESPONSE: " + response.getBody());
        
        // For now, return the same data structure as gainers
        // You can modify this later to filter for actual losers
        return response.getBody() != null ? response.getBody().get("trending_stocks") : Collections.emptyList();
    } catch (Exception ex) {
        ex.printStackTrace();
        return Collections.emptyList();
    }

    }
}
