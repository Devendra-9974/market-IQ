package com.stockpredictor.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "stocks")  // MongoDB collection name
public class Stock {

    @Id
    private String id;          // unique identifier
    private String ticker;      // stock symbol, e.g., AAPL
    private double predictedPrice;
    private String date;        // you can use Date type too

//    public Stock() {}

    public Stock(String ticker, double predictedPrice, String date) {
        this.ticker = ticker;
        this.predictedPrice = predictedPrice;
        this.date = date;
    }

    // --- Getters and Setters ---
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getPredictedPrice() {
        return predictedPrice;
    }

    public void setPredictedPrice(double predictedPrice) {
        this.predictedPrice = predictedPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
