package org.cowary.arttrackerback.dbCase.movie;

import org.cowary.arttrackerback.entity.movie.MovieIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.movie.MovieIntegrationRepo;

@Component
public class MovieIntegrationCrud {

    @Autowired
    MovieIntegrationRepo movieIntegrationRepo;

    public void create(long idIntegration, String name, long movieId) {
        MovieIntegration movieIntegration = new MovieIntegration(name, movieId, idIntegration);
        movieIntegrationRepo.save(movieIntegration);
    }
}
