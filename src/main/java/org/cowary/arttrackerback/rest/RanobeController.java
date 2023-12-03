package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.ranobe.RanobeCrud;
import org.cowary.arttrackerback.entity.api.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.api.findRs.Finds;
import org.cowary.arttrackerback.entity.api.mediaRs.RanobeRs;
import org.cowary.arttrackerback.entity.ranobe.Ranobe;
import org.cowary.arttrackerback.integration.api.shiki.ShikimoriApi;
import org.cowary.arttrackerback.integration.model.shiki.RanobeModel;
import org.cowary.arttrackerback.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/title")
public class RanobeController implements TitleInterface<Ranobe>, FindController<RanobeRs> {

    @Autowired
    RanobeCrud ranobeCrud;

    @Override
    @GetMapping("/ranobe")
    public ResponseEntity<List<Ranobe>> getAllByUsrId(@RequestHeader long userId) {
        return ResponseEntity.ok(
                ranobeCrud.getAllByUserId(userId)
        );
    }

    @Override
    @GetMapping("/ranobe/{titleId}")
    public ResponseEntity<Ranobe> getTitle(@PathVariable long titleId) {
        return ResponseEntity.ok(
                ranobeCrud.findById(titleId)
        );
    }

    @Override
    public ResponseEntity<Ranobe> postTitle(Ranobe title) {
        ranobeCrud.save(title);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    public ResponseEntity<Ranobe> putTitle(Ranobe title) {
        ranobeCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    public ResponseEntity<String> deleteTitle(long id) {
        ranobeCrud.deleteById(id);
        return ResponseEntity.ok(String.format("ranobe №%s deleted", id));
    }

    @Override
    @GetMapping("/ranobe/find")
    public ResponseEntity<FindMediaRs> find(@PathVariable String keyword) {
        var mediaModelList = ShikimoriApi.ranobeApi().searchByName(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (RanobeModel ranobeModel : mediaModelList) {
            var fins = new Finds(ranobeModel.getName(), ranobeModel.getRussian(), ranobeModel.getScore(), ranobeModel.getChapters(), DateFormat.HTMLshort.parse(ranobeModel.getAired_on()).getYear(), ranobeModel.getId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }

    @Override
    public ResponseEntity getByIntegrationID(int id) {
        return null;
    }

}
