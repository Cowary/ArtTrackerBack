package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.anime.AnimeCrud;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class AnimeController implements TitleImpl<Anime> {

    @Autowired
    AnimeCrud animeCrud;

    @Override
    @GetMapping("/anime")
    public List<Anime> getAllByUsrId(@RequestHeader long userId) {
        return animeCrud.getAllByUserId(userId);
    }

    @Override
    @GetMapping("/anime/{titleId}")
    public Anime getTitle(@PathVariable long titleId) {
        return animeCrud.getById(titleId);
    }

    @Override
    public Anime postTitle(Anime title) {
        return null;
    }

    @Override
    public Anime putTitle(Anime title) {
        return null;
    }

    @Override
    public Anime deleteTitle(Anime title) {
        return null;
    }
}
