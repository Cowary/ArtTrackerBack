package org.cowary.arttrackerback.repo.ranobe;

import org.cowary.arttrackerback.entity.ranobe.RanobePublisher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RanobePublisherRepo extends CrudRepository<RanobePublisher, Long> {

    List<RanobePublisher> findByRanobeId(long ranobeId);
}
