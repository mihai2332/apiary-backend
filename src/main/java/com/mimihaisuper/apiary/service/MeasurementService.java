package com.mimihaisuper.apiary.service;

import com.google.common.collect.Iterables;
import com.mimihaisuper.apiary.model.AcquisitionModule;
import com.mimihaisuper.apiary.model.Measurement;
import com.mimihaisuper.apiary.model.Sensor;
import com.mimihaisuper.apiary.model.dto.SensorChartDTO;
import com.mimihaisuper.apiary.repository.AcquisitionModuleRepository;
import com.mimihaisuper.apiary.repository.MeasurementRepository;
import com.mimihaisuper.apiary.repository.SensorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class MeasurementService {
    private Logger logger = LoggerFactory.getLogger(MeasurementService.class);

    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AcquisitionModuleRepository acquisitionModuleRepository;
    @Autowired
    SensorRepository sensorRepository;

    public Set<Measurement> getMeasurementsInDataRange(SensorChartDTO sensorChartDTO) {
        AcquisitionModule acquisitionModule = acquisitionModuleRepository.findByUuid(sensorChartDTO.moduleUUID);
        Sensor sensor = sensorRepository.findBySensorTypeAndAcquisitionModule(sensorChartDTO.sensorName, acquisitionModule);
        return measurementRepository.findAllByCreationDateBetweenAndSensor(sensorChartDTO.beginDate, sensorChartDTO.endDate, sensor);
    }

    public Set<Measurement> getAllMeasurements(SensorChartDTO sensorChartDTO) {
        AcquisitionModule acquisitionModule = acquisitionModuleRepository.findByUuid(sensorChartDTO.moduleUUID);
        Sensor sensor = sensorRepository.findBySensorTypeAndAcquisitionModule(sensorChartDTO.sensorName, acquisitionModule);
        Set<Measurement> measurements = measurementRepository.findAllBySensor(sensor);
        if (measurements.size() < 50) {
            return measurements;
        }
        Set<Measurement> newMeasurements = decimateMeasurements(measurements);
        return newMeasurements;
    }

    private Set<Measurement> decimateMeasurements(Set<Measurement> measurements) {
        Set<Measurement> newMeasurements = new HashSet<>();
        int numberInAGroup = (int) Math.floor(measurements.size() / 50);
        for (int i = 0; i < 49; i++) {
            Iterable<Measurement> groupedMeasurements = Iterables.limit(Iterables.skip(measurements, i * numberInAGroup), numberInAGroup);
            float sum = 0.0F;
            for (Measurement measurement : groupedMeasurements) {
                sum = sum + Float.valueOf(measurement.getValue());
            }
            Float avg = sum / numberInAGroup;
            createSetOfMeasurements(groupedMeasurements.iterator().next(), avg, newMeasurements);
        }
        if (measurements.size() % 50 != 0) {
            Iterable<Measurement> groupedMeasurements = Iterables.limit(Iterables.skip(measurements, 49 * numberInAGroup), numberInAGroup);
            float sum = 0.0F;
            for (Measurement measurement : groupedMeasurements) {
                sum = sum + Float.valueOf(measurement.getValue());
            }
            Float avg = sum / (measurements.size() % 50);
            createSetOfMeasurements(groupedMeasurements.iterator().next(), avg, newMeasurements);
        }
        return newMeasurements;
    }

    private void createSetOfMeasurements(Measurement measurement, Float avg, Set<Measurement> newMeasurements) {
        Date date = measurement.getCreationDate();
        Sensor sensor = measurement.getSensor();
        newMeasurements.add(new Measurement(String.valueOf(avg), date, sensor));
    }
}
