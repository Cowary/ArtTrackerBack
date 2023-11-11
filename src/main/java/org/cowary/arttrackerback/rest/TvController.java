package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.tv.TvCrud;
import org.cowary.arttrackerback.entity.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.findRs.Finds;
import org.cowary.arttrackerback.entity.tv.Tv;
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
public class TvController implements TitleInterface<Tv>, FindController {

    @Autowired
    private TvCrud tvCrud;

    @Override
    @GetMapping("/tv")
    public ResponseEntity<List<Tv>> getAllByUsrId(@RequestHeader long userId) {
        return ResponseEntity.ok(
                tvCrud.getAllByUserId(userId)
        );
    }

    @Override
    @GetMapping("/tv/{titleId}")
    public ResponseEntity<Tv> getTitle(@PathVariable long titleId) {
        return ResponseEntity.ok(
                tvCrud.findById(titleId)
        );
    }

    @Override
    public ResponseEntity<Tv> postTitle(Tv title) {
        tvCrud.save(title);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    public ResponseEntity<Tv> putTitle(Tv title) {
        tvCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    public ResponseEntity<String> deleteTitle(long id) {
        tvCrud.deleteById(id);
        return ResponseEntity.ok(String.format("ranobe №%s deleted", id));
    }

    @Override
    @GetMapping("/tv/find")
    public ResponseEntity<FindMediaRs> find(String keyword) {
        var mediaModelList = KinApi.serialApi().searchByKeyword(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (KinResultModel kinResultModel: mediaModelList) {
            var fins = new Finds(kinResultModel.getNameEn(), kinResultModel.getNameRu(), kinResultModel.getRating().toString(), 1, Integer.valueOf(kinResultModel.getYear()), kinResultModel.getFilmId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }
}
