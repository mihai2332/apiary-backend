package com.mimihaisuper.apiary.repository;

import com.mimihaisuper.apiary.model.Measurement;
import com.mimihaisuper.apiary.model.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface MeasurementRepository extends CrudRepository<Measurement, Long> {
    Set<Measurement> findAllByCreationDateBetweenAndSensor(Date begin, Date end, Sensor sensor);

    Set<Measurement> findAllBySensor(Sensor sensor);
}
