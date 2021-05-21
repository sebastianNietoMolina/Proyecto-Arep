package com.prueba.apache.kafka.persistence;

import com.prueba.apache.kafka.model.City;

public interface GeneralRepository {

    public void insertData(City data);
}
