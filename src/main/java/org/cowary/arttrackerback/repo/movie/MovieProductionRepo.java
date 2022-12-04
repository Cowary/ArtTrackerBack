package org.cowary.arttrackerback.repo.movie;

import org.cowary.arttrackerback.entity.movie.MovieProduction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieProductionRepo extends CrudRepository<MovieProduction, Long> {

    List<MovieProduction> findById(long id);
}
