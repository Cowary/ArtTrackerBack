package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.manga.MangaCrud;
import org.cowary.arttrackerback.entity.manga.Manga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class MangaController implements TitleInterface<Manga> {

    @Autowired
    private MangaCrud mangaCrud;

    @Override
    @GetMapping("/manga")
    public ResponseEntity<List<Manga>> getAllByUsrId(@RequestHeader long userId) {
        return ResponseEntity.ok(
                mangaCrud.findAllByUserId(userId)
        );
    }

    @Override
    @GetMapping("/manga/{titleId}")
    public ResponseEntity<Manga> getTitle(@PathVariable long titleId) {
        return ResponseEntity.ok(
                mangaCrud.findByUserId(titleId)
        );
    }

    @Override
    public ResponseEntity<Manga> postTitle(Manga title) {
        mangaCrud.save(title);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    public ResponseEntity<Manga> putTitle(Manga title) {
        mangaCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    public ResponseEntity<String> deleteTitle(long id) {
        mangaCrud.deleteById(id);
        return ResponseEntity.ok(String.format("Manga №%s deleted", id));
    }
}
