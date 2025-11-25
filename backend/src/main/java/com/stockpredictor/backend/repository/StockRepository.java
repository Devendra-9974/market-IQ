package com.stockpredictor.backend.repository;

import com.stockpredictor.backend.model.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, String> {
    // Later we can add custom queries like findByTicker()
}
