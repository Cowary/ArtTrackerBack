package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.tv.TvCrud;
import org.cowary.arttrackerback.entity.tv.Tv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class TvController implements TitleInterface<Tv> {

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
}
