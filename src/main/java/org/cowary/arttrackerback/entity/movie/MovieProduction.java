package org.cowary.arttrackerback.entity.movie;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Getter
@Setter
@ToString

@Entity(name = "movie_production")
public class MovieProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long movieId;
    private Long productionId;

    public MovieProduction(Long movieId, Long productionId) {
        this.movieId = movieId;
        this.productionId = productionId;
    }

    public MovieProduction() {
    }
}
