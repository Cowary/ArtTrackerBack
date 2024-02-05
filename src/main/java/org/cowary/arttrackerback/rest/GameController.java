package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import lombok.Setter;
import org.cowary.arttrackerback.dbCase.game.GameCrud;
import org.cowary.arttrackerback.entity.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
@Setter
public class GameController implements TitleController<Game> {

    @Autowired
    private GameCrud gameCrud;

    @Override
    @GetMapping("/game")
    public ResponseEntity<List<Game>> getAllByUsrId(@RequestHeader long userId) {
        return ResponseEntity.ok(
                gameCrud.getAllByUserId(userId)
        );
    }

    @Override
    @GetMapping("/game/{titleId}")
    public ResponseEntity<Game> getTitle(@PathVariable long titleId) {
        return ResponseEntity.ok(
                gameCrud.findById(titleId)
        );
    }

    @Override
    @PostMapping("/game")
    public ResponseEntity<Game> postTitle(@Valid @RequestBody Game title) {
        gameCrud.save(title);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    @PutMapping("/game")
    public ResponseEntity<Game> putTitle(@Valid @RequestBody Game title) {
        gameCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    @DeleteMapping("/game")
    public ResponseEntity<String> deleteTitle(@RequestHeader long id) {
        gameCrud.deleteById(id);
        return ResponseEntity.ok(String.format("game №%s deleted", id));
    }
}
