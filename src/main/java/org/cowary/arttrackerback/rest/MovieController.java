package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.movie.MovieCrud;
import org.cowary.arttrackerback.entity.api.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.api.findRs.Finds;
import org.cowary.arttrackerback.entity.api.mediaRs.MovieRs;
import org.cowary.arttrackerback.entity.movie.Movie;
import org.cowary.arttrackerback.integration.api.kin.KinApi;
import org.cowary.arttrackerback.integration.model.kin.KinResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/title")
public class MovieController implements TitleInterface<Movie>, FindController {

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
    @PostMapping("/movie")
    public ResponseEntity<Movie> postTitle(Movie title) {
        movieCrud.save(title);
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    @PutMapping("/movie")
    public ResponseEntity<Movie> putTitle(Movie title) {
        movieCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    @DeleteMapping("/movie")
    public ResponseEntity<String> deleteTitle(long id) {
        movieCrud.deleteById(id);
        return ResponseEntity.ok(String.format("movie №%s deleted", id));
    }

    @Override
    @GetMapping("/movie/find")
    public ResponseEntity<FindMediaRs> find(@PathVariable String keyword) {
        var mediaModelList = KinApi.filmApi().searchByKeyword(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (KinResultModel kinResultModel: mediaModelList) {
            var fins = new Finds(kinResultModel.getNameEn(), kinResultModel.getNameRu(), kinResultModel.getRating(), 1, Integer.valueOf(kinResultModel.getYear()), kinResultModel.getFilmId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }

    @Override
    @GetMapping("/movie/getByServiceId")
    public ResponseEntity<MovieRs> getByIntegrationID(@RequestParam int id) {
        var kinFilmModel = KinApi.filmApi().getById(id);
        var movie = new Movie(kinFilmModel.getNameOriginal(), kinFilmModel.getNameRu(), kinFilmModel.getYear(), kinFilmModel.getFilmLength());
        return ResponseEntity.ok(new MovieRs(movie, kinFilmModel.getPosterUrl()));
    }

}
