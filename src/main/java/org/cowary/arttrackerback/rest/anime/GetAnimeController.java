package org.cowary.arttrackerback.rest.anime;

import org.cowary.arttrackerback.dbCase.anime.AnimeCrud;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.rest.FindImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GetAnimeController implements FindImpl<Anime> {

    @Autowired
    AnimeCrud animeCrud;

    @GetMapping("/anime")
    @Override
    public List<Anime> findByUsrId(@RequestHeader long userId) {
        return animeCrud.getAll(userId);
    }

    @Override
    @GetMapping("/anime/{titleId}")
    public Anime findTitle(@RequestHeader long userId, @PathVariable long titleId) {
        return animeCrud.getById(titleId);
    }
}
