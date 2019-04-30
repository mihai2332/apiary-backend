package com.mimihaisuper.apiary.service;

import com.mimihaisuper.apiary.model.AcquisitionModule;
import com.mimihaisuper.apiary.model.Sensor;
import com.mimihaisuper.apiary.repository.AcquisitionModuleRepository;
import com.mimihaisuper.apiary.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorService {
    @Autowired
    AcquisitionModuleRepository acquisitionModuleRepository;
    @Autowired
    SensorRepository sensorRepository;

    public void deleteSensor(String uuid, String sensorType) {
        AcquisitionModule module = acquisitionModuleRepository.findByUuid(uuid);
        Sensor sensor = sensorRepository.findBySensorTypeAndAcquisitionModule(sensorType, module);
        sensorRepository.delete(sensor);
    }
}
