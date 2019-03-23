package com.mimihaisuper.apiary.repository;


import com.mimihaisuper.apiary.model.Role;
import com.mimihaisuper.apiary.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
