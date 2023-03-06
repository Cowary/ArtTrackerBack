package org.cowary.arttrackerback.rest.anime;

import org.cowary.arttrackerback.dbCase.manga.MangaCrud;
import org.cowary.arttrackerback.entity.manga.Manga;
import org.cowary.arttrackerback.rest.GetTitleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class MangaGetController implements GetTitleImpl<Manga> {

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
}
