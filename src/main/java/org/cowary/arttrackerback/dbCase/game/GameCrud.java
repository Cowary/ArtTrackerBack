package org.cowary.arttrackerback.dbCase.game;

import org.cowary.arttrackerback.dbCase.UserService;
import org.cowary.arttrackerback.entity.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.game.GameRepo;
import org.cowary.arttrackerback.util.DateUtil;

import java.util.List;

@Component
public class GameCrud {

    @Autowired
    GameRepo gameRepo;
    @Autowired
    UserService userService;

    public List<Game> getAll(String status) {
        long userId = userService.getIdCurrentUser();
        if(status.equals("")) return gameRepo.findAllByUsrId(userId);
        else return gameRepo.findByStatus(status);
    }

    public Game findById(long id) {
        return gameRepo.findById(id).orElseThrow();
    }

    public void save(Game game) {
        game.setLastUpd(DateUtil.now());
        game.setUsrId(userService.getIdCurrentUser());
        if(game.getReleaseDate() != null) game.setReleaseYear(DateUtil.getYear(game.getReleaseDate()));
        gameRepo.save(game);
    }

    public void delete(Game game) {
        gameRepo.delete(game);
    }
}
