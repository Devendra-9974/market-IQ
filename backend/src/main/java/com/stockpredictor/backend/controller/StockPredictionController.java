package com.stockpredictor.backend.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;
import java.util.Comparator;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// import com.stockpredictor.backend.service.LstmPredictionService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class StockPredictionController {

    @Value("${alpha.api.key}") // ✅ Loaded from application.properties
    private String apiKey;

   @PostMapping("/predict")
public Map<String, Object> predictStock(@RequestBody Map<String, String> request) {
    String ticker = request.get("ticker");
    Map<String, Object> response = new HashMap<>();

    try {
        String apiUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="
                + ticker + "&apikey=" + apiKey + "&outputsize=compact";

        HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            response.put("error", "Failed to fetch data: HTTP " + conn.getResponseCode());
            return response;
        }

        Scanner sc = new Scanner(conn.getInputStream());
        StringBuilder jsonResponse = new StringBuilder();
        while (sc.hasNext()) jsonResponse.append(sc.nextLine());
        sc.close();

        JSONObject json = new JSONObject(jsonResponse.toString());
        JSONObject ts = json.getJSONObject("Time Series (Daily)");

        // 👉 convert to sorted list (most recent last)
        List<Map<String, Object>> history = new ArrayList<>();

        ts.keys().forEachRemaining(dateStr -> {
            JSONObject day = ts.getJSONObject(dateStr);
            Map<String, Object> point = new HashMap<>();
            point.put("date", dateStr);
            point.put("close", day.getDouble("4. close"));
            history.add(point);
        });

        // sort ascending by date
        history.sort(Comparator.comparing(p -> p.get("date").toString()));

        // latest price
        Map<String, Object> lastPoint = history.get(history.size() - 1);
        double closePrice = (Double) lastPoint.get("close");

        double predictedPrice = closePrice * 1.05;

        response.put("ticker", ticker);
        response.put("currentPrice", closePrice);
        response.put("predictedPrice", predictedPrice);
        response.put("date", lastPoint.get("date"));
        response.put("history", history.subList(Math.max(0, history.size() - 60), history.size()));

    } catch (Exception e) {
        response.put("error", "Error: " + e.getMessage());
    }


    
    return response;
}

}
