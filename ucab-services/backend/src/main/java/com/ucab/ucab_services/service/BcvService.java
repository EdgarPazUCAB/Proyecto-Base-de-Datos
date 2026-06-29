package com.ucab.ucab_services.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class BcvService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://pydolarvenezuela-api.vercel.app/api/v1/dollar/page?page=bcv";

    public Double getBcvRate() {
        try {
            Map<String, Object> response = restTemplate.getForObject(API_URL, Map.class);
            if (response != null && response.containsKey("monitors")) {
                Map<String, Object> monitors = (Map<String, Object>) response.get("monitors");
                if (monitors.containsKey("usd")) {
                    Map<String, Object> usdInfo = (Map<String, Object>) monitors.get("usd");
                    if (usdInfo.containsKey("price")) {
                        Object priceObj = usdInfo.get("price");
                        if (priceObj instanceof Number) {
                            return ((Number) priceObj).doubleValue();
                        } else if (priceObj instanceof String) {
                            return Double.parseDouble((String) priceObj);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Fallback en caso de error
        return 36.50; 
    }
}
