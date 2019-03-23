package com.mimihaisuper.apiary.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SENSOR")
public class Sensor {

    @Id
    @GeneratedValue
    @Column(name = "SENSOR_ID")
    private Long id;

    @Column(name = "TYPE")
    private String sensorType;

    @ManyToOne
    @JoinColumn(name = "MODULE_ID")
    private AcquisitionModule acquisitionModule;

    @OneToMany(mappedBy = "sensor")
    private Set<Measurement> measurements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public AcquisitionModule getAcquisitionModule() {
        return acquisitionModule;
    }

    public void setAcquisitionModule(AcquisitionModule acquisitionModule) {
        this.acquisitionModule = acquisitionModule;
    }

    public Set<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Set<Measurement> measurements) {
        this.measurements = measurements;
    }
}
