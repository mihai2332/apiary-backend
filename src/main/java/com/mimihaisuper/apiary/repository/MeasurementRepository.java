package com.mimihaisuper.apiary.repository;

import com.mimihaisuper.apiary.model.Measurement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends CrudRepository<Measurement, Long> {
}
