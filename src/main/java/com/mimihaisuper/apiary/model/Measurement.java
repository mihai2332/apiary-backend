package com.mimihaisuper.apiary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MEASUREMENT")
public class Measurement {

    @Id
    @GeneratedValue
    @Column(name = "MEASUREMENT_ID")
    private Long id;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "DATE")
    private Date creationDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "SENSOR_ID")
    private Sensor sensor;

    public Measurement(String value, Date creationDate, Sensor sensor) {
        this.value = value;
        this.creationDate = creationDate;
        this.sensor = sensor;
    }

    public Measurement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

}
