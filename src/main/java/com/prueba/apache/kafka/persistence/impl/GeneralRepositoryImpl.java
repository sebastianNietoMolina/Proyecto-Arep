package com.prueba.apache.kafka.persistence.impl;

import com.prueba.apache.kafka.model.City;
import com.prueba.apache.kafka.persistence.GeneralRepository;
import com.prueba.apache.kafka.persistence.repositories.WeaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneralRepositoryImpl implements GeneralRepository {

    @Autowired
    WeaterRepository weaterRepository;

    @Override
    public void insertData(City data) {
        weaterRepository.insert(data);
    }
}
