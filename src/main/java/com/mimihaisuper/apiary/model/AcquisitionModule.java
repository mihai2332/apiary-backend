package com.mimihaisuper.apiary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mimihaisuper.apiary.model.authModel.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ACQUISITION_MODULE")
public class AcquisitionModule {
    @Id
    @GeneratedValue
    @Column(name = "MODULE_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "acquisitionModule")
    @JsonIgnore
    private Set<Sensor> sensors;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "USER_ID")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }
}
