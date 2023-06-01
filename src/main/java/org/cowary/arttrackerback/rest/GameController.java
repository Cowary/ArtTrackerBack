package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.game.GameCrud;
import org.cowary.arttrackerback.entity.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class GameController implements TitleImpl<Game> {

    @Autowired
    GameCrud gameCrud;

    @Override
    @GetMapping("/game")
    public List<Game> getAllByUsrId(@RequestHeader long userId) {
        return gameCrud.getAllByUserId(userId);
    }

    @Override
    @GetMapping("/game/{titleId}")
    public Game getTitle(@PathVariable long titleId) {
        return gameCrud.findById(titleId);
    }

    @Override
    public ResponseEntity<Game> postTitle(Game title) {
        return null;
    }

    @Override
    public ResponseEntity<Game> putTitle(Game title) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteTitle(long id) {
        return null;
    }
}
