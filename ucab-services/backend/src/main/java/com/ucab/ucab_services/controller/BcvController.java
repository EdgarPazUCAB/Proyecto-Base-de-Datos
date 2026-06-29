package com.ucab.ucab_services.controller;

import com.ucab.ucab_services.service.BcvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/tasa-bcv")
@CrossOrigin(origins = "http://localhost:4200")
public class BcvController {

    @Autowired
    private BcvService bcvService;

    @GetMapping
    public Map<String, Double> getRate() {
        Map<String, Double> response = new HashMap<>();
        response.put("rate", bcvService.getBcvRate());
        return response;
    }
}
