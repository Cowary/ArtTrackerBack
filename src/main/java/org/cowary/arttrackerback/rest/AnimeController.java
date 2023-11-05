package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import org.cowary.arttrackerback.dbCase.anime.AnimeCrud;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class AnimeController implements TitleInterface<Anime> {

    @Autowired
    AnimeCrud animeCrud;

    @Override
    @GetMapping("/anime")
    public ResponseEntity<List<Anime>> getAllByUsrId(@RequestHeader long userId) {
        return ResponseEntity.ok(
                animeCrud.getAllByUserId(userId)
        );
    }

    @Override
    @GetMapping("/anime/{titleId}")
    public ResponseEntity<Anime> getTitle(@PathVariable long titleId) {
        return ResponseEntity.ok(
                animeCrud.getById(titleId)
        );
    }

    @Override
    @PostMapping("/anime")
    public ResponseEntity<Anime> postTitle(@Valid @RequestBody Anime title) {
        animeCrud.save(title);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    @PutMapping("/anime")
    public ResponseEntity<Anime> putTitle(Anime title) {
        animeCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    @DeleteMapping("/anime")
    public ResponseEntity<String> deleteTitle(@RequestHeader long id) {
        animeCrud.deleteById(id);
        return ResponseEntity.ok(String.format("anime №%s deleted", id));
    }


}
