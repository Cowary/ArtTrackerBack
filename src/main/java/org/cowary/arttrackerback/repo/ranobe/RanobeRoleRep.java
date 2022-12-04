package org.cowary.arttrackerback.repo.ranobe;

import org.cowary.arttrackerback.entity.ranobe.RanobeRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RanobeRoleRep extends CrudRepository<RanobeRole, Long> {

    List<RanobeRole> findByRanobeId(int ranobeId);
}
