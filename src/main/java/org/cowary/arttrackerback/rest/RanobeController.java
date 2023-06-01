package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.ranobe.RanobeCrud;
import org.cowary.arttrackerback.entity.ranobe.Ranobe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class RanobeController implements TitleImpl<Ranobe> {

    @Autowired
    RanobeCrud ranobeCrud;

    @Override
    @GetMapping("/ranobe")
    public List<Ranobe> getAllByUsrId(@RequestHeader long userId) {
        return ranobeCrud.getAllByUserId(userId);
    }

    @Override
    @GetMapping("/ranobe/{titleId}")
    public Ranobe getTitle(@PathVariable long titleId) {
        return ranobeCrud.findById(titleId);
    }

    @Override
    public ResponseEntity<Ranobe> postTitle(Ranobe title) {
        return null;
    }

    @Override
    public ResponseEntity<Ranobe> putTitle(Ranobe title) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteTitle(long id) {
        return null;
    }
}
