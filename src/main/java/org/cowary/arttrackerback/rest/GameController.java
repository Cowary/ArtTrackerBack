package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import lombok.Setter;
import org.cowary.arttrackerback.dbCase.game.GameCrud;
import org.cowary.arttrackerback.entity.game.Game;
import org.cowary.arttrackerback.rest.converter.GameConverter;
import org.cowary.arttrackerback.rest.dto.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
@Setter
public class GameController implements TitleController<GameDto> {

    @Autowired
    private GameCrud gameCrud;

    @Override
    @GetMapping("/game")
    public ResponseEntity<List<GameDto>> getAllByUsrId(@RequestHeader long userId) {
        var gameList = gameCrud.getAllByUserId(userId);
        var gameDtoList = gameList.stream().map(GameConverter::convert).toList();
        return ResponseEntity.ok(
                gameDtoList
        );
    }

    @Override
    @GetMapping("/game/{titleId}")
    public ResponseEntity<GameDto> getTitle(@PathVariable long titleId) {
        var game = gameCrud.findById(titleId);
        var gameDto = GameConverter.convert(game);
        return ResponseEntity.ok(
                gameDto
        );
    }

    @Override
    @PostMapping("/game")
    public ResponseEntity<GameDto> postTitle(@Valid @RequestBody GameDto title) {
        var game = GameConverter.convert(title);
        gameCrud.save(game);
        title.setId(game.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    @PutMapping("/game")
    public ResponseEntity<GameDto> putTitle(@Valid @RequestBody GameDto title) {
        var game = GameConverter.convert(title);
        gameCrud.save(game);
        return ResponseEntity.ok(title);
    }

    @Override
    @DeleteMapping("/game")
    public ResponseEntity<String> deleteTitle(@RequestHeader long id) {
        gameCrud.deleteById(id);
        return ResponseEntity.ok(String.format("game №%s deleted", id));
    }
}
