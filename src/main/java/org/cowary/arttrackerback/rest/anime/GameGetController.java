package org.cowary.arttrackerback.rest.anime;

import org.cowary.arttrackerback.dbCase.game.GameCrud;
import org.cowary.arttrackerback.entity.game.Game;
import org.cowary.arttrackerback.rest.GetTitleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class GameGetController implements GetTitleImpl<Game> {

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
}
