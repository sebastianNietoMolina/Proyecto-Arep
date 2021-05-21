package com.prueba.apache.kafka.persistence.repositories;

import com.prueba.apache.kafka.model.City;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeaterRepository extends MongoRepository<City, String> {
}
