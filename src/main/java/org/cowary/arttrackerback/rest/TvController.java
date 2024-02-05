package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.cowary.arttrackerback.dbCase.tv.TvCrud;
import org.cowary.arttrackerback.dbCase.tv.TvSeasonsCrud;
import org.cowary.arttrackerback.entity.api.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.api.findRs.Finds;
import org.cowary.arttrackerback.entity.api.mediaRs.TvRs;
import org.cowary.arttrackerback.entity.tv.Tv;
import org.cowary.arttrackerback.entity.tv.TvSeason;
import org.cowary.arttrackerback.integration.api.kin.KinApi;
import org.cowary.arttrackerback.integration.model.kin.KinResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/title")
@Setter
public class TvController implements TitleController<TvSeason>, FindController<TvRs> {

    @Autowired
    private TvSeasonsCrud tvSeasonsCrud;
    @Autowired
    private TvCrud tvCrud;

    @Override
    @GetMapping("/tv")
    public ResponseEntity<List<TvSeason>> getAllByUsrId(@RequestHeader long userId) {
        return ResponseEntity.ok(
                tvSeasonsCrud.getAllByUserId(userId)
        );
    }

    @Override
    @GetMapping("/tv/{titleId}")
    public ResponseEntity<TvSeason> getTitle(@PathVariable long titleId) {
        return ResponseEntity.ok(
                tvSeasonsCrud.getById(titleId)
        );
    }

    @Override
    @PostMapping("/tv")
    public ResponseEntity<TvSeason> postTitle(@Valid @RequestBody TvSeason title) {
        tvSeasonsCrud.save(title);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    @PutMapping("/tv")
    public ResponseEntity<TvSeason> putTitle(@Valid @RequestBody TvSeason title) {
        tvSeasonsCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    @DeleteMapping("/tv")
    public ResponseEntity<String> deleteTitle(@RequestHeader long id) {
        tvSeasonsCrud.deleteById(id);
        return ResponseEntity.ok(String.format("ranobe №%s deleted", id));
    }

    @Override
    @GetMapping("/tv/find")
    public ResponseEntity<FindMediaRs> find(@RequestParam @NotBlank String keyword) {
        var mediaModelList = KinApi.serialApi().searchByKeyword(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (KinResultModel kinResultModel: mediaModelList) {
            var releaseYear = kinResultModel.getYear().equals("null") ? 0 : Integer.parseInt(kinResultModel.getYear());
            var fins = new Finds(kinResultModel.getNameEn(), kinResultModel.getNameRu(), kinResultModel.getRating(), 1, releaseYear, kinResultModel.getFilmId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }

    @Override
    @GetMapping("/tv/getByServiceId")
    public ResponseEntity<TvRs> getByIntegrationID(@RequestParam @NotNull int id) {
        var tvModel = KinApi.filmApi().getById(id);
        var actualTv = tvCrud.findByOriginalTitleAndUserId(tvModel.getNameOriginal());
        var tv = new Tv(tvModel.getNameOriginal(), tvModel.getNameRu(), tvModel.getYear(), 1, id);
        return ResponseEntity.ok(
                new TvRs(Objects.requireNonNullElse(actualTv, tv), tvModel.getPosterUrl())
        );
    }

}
