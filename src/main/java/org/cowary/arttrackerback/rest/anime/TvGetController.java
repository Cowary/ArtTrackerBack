package org.cowary.arttrackerback.rest.anime;

import org.cowary.arttrackerback.dbCase.tv.TvCrud;
import org.cowary.arttrackerback.entity.tv.Tv;
import org.cowary.arttrackerback.rest.GetTitleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class TvGetController implements GetTitleImpl<Tv> {

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
}
