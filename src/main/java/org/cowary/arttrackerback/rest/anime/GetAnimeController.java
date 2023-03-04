package org.cowary.arttrackerback.rest.anime;

import org.cowary.arttrackerback.dbCase.anime.AnimeCrud;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.rest.FindImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GetAnimeController implements FindImpl<Anime> {

    @Autowired
    AnimeCrud animeCrud;

    // TODO: 04.03.2023 Переделать
    @GetMapping("/animes")
    @Override
    public List<Anime> findByUsrId(@RequestParam long userId) {
        return animeCrud.getAll(userId);
    }

    @Override
    @GetMapping("/anime")
    public Anime findTitle(@RequestParam long userId, @RequestParam long titleId) {
        return animeCrud.getById(titleId);
    }
}
