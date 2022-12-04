package org.cowary.arttrackerback.repo.movie;

import org.cowary.arttrackerback.entity.movie.MovieIntegration;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MovieIntegrationRepo extends CrudRepository<MovieIntegration, Long> {

    Optional<MovieIntegration> findByNameAndIdIntegration(String name, Long integrationId);
}
