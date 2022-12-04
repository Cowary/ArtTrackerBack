package org.cowary.arttrackerback.dbCase.movie;

import org.cowary.arttrackerback.dbCase.ProductionCrud;
import org.cowary.arttrackerback.entity.Production;
import org.cowary.arttrackerback.entity.movie.MovieProduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.movie.MovieProductionRepo;

@Component
public class MovieProductionCrud {

    @Autowired
    MovieProductionRepo movieProductionRepo;
    @Autowired
    ProductionCrud productionCrud;

    public void create(String productionName, Long movieId) {
        Production production = productionCrud.createOrGetByName(productionName);
        MovieProduction movieProduction = new MovieProduction(movieId, production.getId());
        movieProductionRepo.save(movieProduction);
    }
}
