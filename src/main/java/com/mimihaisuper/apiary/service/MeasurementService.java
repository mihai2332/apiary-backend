package com.mimihaisuper.apiary.service;

import com.mimihaisuper.apiary.model.AcquisitionModule;
import com.mimihaisuper.apiary.model.Sensor;
import com.mimihaisuper.apiary.model.authModel.User;
import com.mimihaisuper.apiary.repository.AcquisitionModuleRepository;
import com.mimihaisuper.apiary.repository.MeasurementRepository;
import com.mimihaisuper.apiary.repository.SensorRepository;
import com.mimihaisuper.apiary.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class MeasurementService {
    Logger logger = LoggerFactory.getLogger(MeasurementService.class);

    @Autowired
    AcquisitionModuleRepository acquisitionModuleRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    SensorRepository sensorRepository;
    @Autowired
    UserRepository userRepository;

    public Set<AcquisitionModule> getModules(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return acquisitionModuleRepository.findByUser(user.get());
    }

    public void createOrAttachModule(String username, String uuid, String name) {
        Optional<User> user = userRepository.findByUsername(username);
        if (acquisitionModuleRepository.existsByUuid(uuid)) {
            AcquisitionModule acquisitionModule = acquisitionModuleRepository.findByUuid(uuid);
            acquisitionModule.setUser(user.get());
            acquisitionModuleRepository.save(acquisitionModule);
            logger.info("{} added to user {}",uuid,username);
        } else {
            AcquisitionModule acquisitionModule = new AcquisitionModule();
            acquisitionModule.setUuid(uuid);
            acquisitionModule.setName(name);
            acquisitionModule.setUser(user.get());
            acquisitionModuleRepository.save(acquisitionModule);
            logger.info("{} created and added to user {}",uuid,username);
        }
    }

    public Set<Sensor> getSensors(String uuid) {
        AcquisitionModule acquisitionModule = acquisitionModuleRepository.findByUuid(uuid);
        return acquisitionModule.getSensors();
    }
}
