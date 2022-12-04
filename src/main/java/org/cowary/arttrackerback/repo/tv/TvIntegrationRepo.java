package org.cowary.arttrackerback.repo.tv;

import org.cowary.arttrackerback.entity.tv.TvIntegration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TvIntegrationRepo extends CrudRepository<TvIntegration, Long> {

    Optional<TvIntegration> findByIdTv(Long tvId);
}
