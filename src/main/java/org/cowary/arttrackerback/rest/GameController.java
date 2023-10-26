package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.game.GameCrud;
import org.cowary.arttrackerback.entity.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class GameController implements TitleInterface<Game> {

    @Autowired
    GameCrud gameCrud;

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
    public ResponseEntity<Game> postTitle(Game title) {
        gameCrud.save(title);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    public ResponseEntity<Game> putTitle(Game title) {
        gameCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    public ResponseEntity<String> deleteTitle(long id) {
        gameCrud.deleteById(id);
        return ResponseEntity.ok(String.format("game №%s deleted", id));
    }
}
