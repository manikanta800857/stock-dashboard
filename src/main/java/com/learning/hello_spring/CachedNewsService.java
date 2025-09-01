package com.learning.hello_spring;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

@Service
public class IndianNewsApiService {

    @Value("${indianapi.base-url}")
    private String baseUrl;

    @Value("${indianapi.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public IndianNewsApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    
    public Object getNews() {
        try {
            String url = baseUrl + "/news";

            HttpHeaders headers = new HttpHeaders();
           headers.set("x-api-key", apiKey);
            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<List> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    List.class
            );

            System.out.println("RAW API RESPONSE: " + response.getBody());

        return response.getBody();
    } catch (Exception ex) {
        ex.printStackTrace();
        return Collections.emptyList();
    }
    }

}
