package com.stockpredictor.backend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Data
@Document(collection = "predictions")
public class Prediction {
    @Id
    private String id;
    private String ticker;
    private List<Object> predictions;
    private String date;
}
