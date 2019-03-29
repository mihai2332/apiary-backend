package com.mimihaisuper.apiary.service;

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
}
