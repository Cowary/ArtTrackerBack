package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.movie.MovieCrud;
import org.cowary.arttrackerback.entity.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class MovieController implements TitleImpl<Movie> {

    @Autowired
    private MovieCrud movieCrud;

    @Override
    @GetMapping("/movie")
    public List<Movie> getAllByUsrId(@RequestHeader long userId) {
        return movieCrud.getAllByUserId(userId);
    }

    @Override
    @GetMapping("/movie/{titleId}")
    public Movie getTitle(@PathVariable long titleId) {
        return movieCrud.findById(titleId);
    }

    @Override
    public ResponseEntity<Movie> postTitle(Movie title) {
        return null;
    }

    @Override
    public ResponseEntity<Movie> putTitle(Movie title) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteTitle(long id) {
        return null;
    }
}
