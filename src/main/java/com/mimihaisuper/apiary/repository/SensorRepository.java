package com.mimihaisuper.apiary.repository;

import com.mimihaisuper.apiary.model.AcquisitionModule;
import com.mimihaisuper.apiary.model.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, String> {
    boolean existsBySensorType(String sensorType);
    Sensor findBySensorType(String sensorType);
    Sensor findBySensorTypeAndAcquisitionModule(String sensorType, AcquisitionModule acquisitionModule);
}
