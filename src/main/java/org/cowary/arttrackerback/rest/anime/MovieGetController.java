package org.cowary.arttrackerback.rest.anime;

import org.cowary.arttrackerback.dbCase.movie.MovieCrud;
import org.cowary.arttrackerback.entity.movie.Movie;
import org.cowary.arttrackerback.rest.GetTitleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class MovieGetController implements GetTitleImpl<Movie> {

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
}
