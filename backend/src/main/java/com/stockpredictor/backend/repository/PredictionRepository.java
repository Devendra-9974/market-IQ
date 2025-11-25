package com.stockpredictor.backend.repository;

import com.stockpredictor.backend.model.Prediction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PredictionRepository extends MongoRepository<Prediction, String> {
}
