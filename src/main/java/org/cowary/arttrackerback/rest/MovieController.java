package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.movie.MovieCrud;
import org.cowary.arttrackerback.entity.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class MovieController implements TitleInterface<Movie> {

    @Autowired
    private MovieCrud movieCrud;

    @Override
    @GetMapping("/movie")
    public ResponseEntity<List<Movie>> getAllByUsrId(@RequestHeader long userId) {
        return ResponseEntity.ok(
                movieCrud.getAllByUserId(userId)
        );
    }

    @Override
    @GetMapping("/movie/{titleId}")
    public ResponseEntity<Movie> getTitle(@PathVariable long titleId) {
        return ResponseEntity.ok(
                movieCrud.findById(titleId)
        );
    }

    @Override
    public ResponseEntity<Movie> postTitle(Movie title) {
        movieCrud.save(title);
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    public ResponseEntity<Movie> putTitle(Movie title) {
        movieCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    public ResponseEntity<String> deleteTitle(long id) {
        movieCrud.deleteById(id);
        return ResponseEntity.ok(String.format("movie №%s deleted", id));
    }
}
