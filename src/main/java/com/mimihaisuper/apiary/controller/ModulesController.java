package com.mimihaisuper.apiary.controller;

import com.mimihaisuper.apiary.model.AcquisitionModule;
import com.mimihaisuper.apiary.model.Measurement;
import com.mimihaisuper.apiary.model.dto.ModuleDTO;
import com.mimihaisuper.apiary.model.dto.SensorChartDTO;
import com.mimihaisuper.apiary.service.MeasurementService;
import com.mimihaisuper.apiary.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/module")
public class ModulesController {
    @Autowired
    ModuleService moduleService;
    @Autowired
    MeasurementService measurementService;

    @GetMapping({"/", ""})
    public ResponseEntity getModules(Principal principal) {
        Set<AcquisitionModule> acquisitionModules = moduleService.getModules(principal.getName());
        return ResponseEntity.ok(acquisitionModules);
    }

    @PostMapping({"/", ""})
    public ResponseEntity createOrAttachModule(@RequestBody ModuleDTO moduleDTO, Principal principal) {
        moduleService.createOrAttachModule(principal.getName(), moduleDTO.uuid, moduleDTO.name);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{uuid}/sensor")
    public ResponseEntity getSensors(@PathVariable(name = "uuid") String uuid) {
        return ResponseEntity.ok(moduleService.getSensors(uuid));
    }

    @PostMapping(value = "/measurement")
    public ResponseEntity getMeasurements(@RequestBody SensorChartDTO sensorChartDTO) {
        Set<Measurement> measurements = measurementService.getMeasurementsInDataRange(sensorChartDTO);
        return ResponseEntity.ok(measurements);
    }
}
