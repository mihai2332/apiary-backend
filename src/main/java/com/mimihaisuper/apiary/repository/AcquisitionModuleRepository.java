package com.mimihaisuper.apiary.repository;

import com.mimihaisuper.apiary.model.AcquisitionModule;
import com.mimihaisuper.apiary.model.authModel.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AcquisitionModuleRepository extends CrudRepository<AcquisitionModule, Long> {
    boolean existsByName(String name);

    boolean existsByUuid(String uuid);

    AcquisitionModule findByName(String name);

    AcquisitionModule findByUuid(String uuid);

    Set<AcquisitionModule> findByUser(User user);
}
