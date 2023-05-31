package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.manga.MangaCrud;
import org.cowary.arttrackerback.entity.manga.Manga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class MangaController implements TitleImpl<Manga> {

    @Autowired
    private MangaCrud mangaCrud;

    @Override
    @GetMapping("/manga")
    public List<Manga> getAllByUsrId(@RequestHeader long userId) {
        return mangaCrud.findAllByUserId(userId);
    }

    @Override
    @GetMapping("/manga/{titleId}")
    public Manga getTitle(@PathVariable long titleId) {
        return mangaCrud.findByUserId(titleId);
    }

    @Override
    public Manga postTitle(Manga title) {
        return null;
    }

    @Override
    public Manga putTitle(Manga title) {
        return null;
    }

    @Override
    public Manga deleteTitle(Manga title) {
        return null;
    }
}