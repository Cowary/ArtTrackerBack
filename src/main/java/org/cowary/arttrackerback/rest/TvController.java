package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.tv.TvCrud;
import org.cowary.arttrackerback.entity.tv.Tv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class TvController implements TitleImpl<Tv> {

    @Autowired
    private TvCrud tvCrud;

    @Override
    @GetMapping("/tv")
    public List<Tv> getAllByUsrId(@RequestHeader long userId) {
        return tvCrud.getAllByUserId(userId);
    }

    @Override
    @GetMapping("/tv/{titleId}")
    public Tv getTitle(@PathVariable long titleId) {
        return tvCrud.findById(titleId);
    }

    @Override
    public Tv postTitle(Tv title) {
        return null;
    }

    @Override
    public Tv putTitle(Tv title) {
        return null;
    }

    @Override
    public Tv deleteTitle(Tv title) {
        return null;
    }
}
