package org.cowary.arttrackerback.dbCase.movie;

import org.cowary.arttrackerback.dbCase.MediaCrud;
import org.cowary.arttrackerback.dbCase.UserService;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.entity.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.movie.MovieRepo;
import org.cowary.arttrackerback.util.DateUtil;

import java.util.List;

@Component
public class MovieCrud implements MediaCrud<Movie> {

    @Autowired
    MovieRepo movieRepo;
    @Autowired
    UserService userService;

    public List<Movie> getAllByUserId(long userId) {
        return movieRepo.findAllByUsrId(userId);
    }

    public Movie findById(long id) {
        return movieRepo.findById(id).orElseThrow();
    }

    public void save(Movie movie) {
        movie.setLastUpd(DateUtil.now());
        movie.setUsrId(userService.getIdCurrentUser());
        movieRepo.save(movie);
    }

    public void delete(Movie movie) {
        movieRepo.delete(movie);
    }

    @Override
    public List<Movie> getAll(long userId, String status) {
        if(status.equals("")) return movieRepo.findAllByUsrId(userId);
        else return movieRepo.findByStatus(status);
    }
}
