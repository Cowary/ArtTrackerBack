package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.cowary.arttrackerback.dbCase.ranobe.RanobeCrud;
import org.cowary.arttrackerback.dbCase.ranobe.RanobeVolumeCrud;
import org.cowary.arttrackerback.entity.api.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.api.findRs.Finds;
import org.cowary.arttrackerback.entity.api.mediaRs.RanobeRs;
import org.cowary.arttrackerback.entity.ranobe.Ranobe;
import org.cowary.arttrackerback.entity.ranobe.RanobeVolume;
import org.cowary.arttrackerback.integration.api.shiki.ShikimoriApi;
import org.cowary.arttrackerback.integration.model.shiki.RanobeModel;
import org.cowary.arttrackerback.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/title")
@Setter
public class RanobeController implements TitleController<RanobeVolume>, FindController<RanobeRs> {

    @Autowired
    private RanobeVolumeCrud ranobeVolumeCrud;
    @Autowired
    private RanobeCrud ranobeCrud;

    @Override
    @GetMapping("/ranobe")
    public ResponseEntity<List<RanobeVolume>> getAllByUsrId(@RequestHeader long userId) {
        return ResponseEntity.ok(
                ranobeVolumeCrud.getAllByUserId(userId)
        );
    }

    @Override
    @GetMapping("/ranobe/{titleId}")
    public ResponseEntity<RanobeVolume> getTitle(@PathVariable long titleId) {
        var ranobe = ranobeVolumeCrud.getById(titleId);
        return ResponseEntity.ok(
                ranobe
        );
    }

    @Override
    @PostMapping("/ranobe")
    public ResponseEntity<RanobeVolume> postTitle(@Valid @RequestBody RanobeVolume title) {
        ranobeVolumeCrud.save(title);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    @PutMapping("/ranobe")
    public ResponseEntity<RanobeVolume> putTitle(@Valid @RequestBody RanobeVolume title) {
        ranobeVolumeCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    @DeleteMapping("/ranobe")
    public ResponseEntity<String> deleteTitle(@RequestHeader long id) {
        ranobeVolumeCrud.deleteById(id);
        return ResponseEntity.ok(String.format("ranobe №%s deleted", id));
    }

    @Override
    @GetMapping("/ranobe/find")
    public ResponseEntity<FindMediaRs> find(@RequestParam @NotBlank String keyword) {
        var mediaModelList = ShikimoriApi.ranobeApi().searchByName(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (RanobeModel ranobeModel : mediaModelList) {
            var fins = new Finds(ranobeModel.getName(), ranobeModel.getRussian(), ranobeModel.getScore(), ranobeModel.getChapters(),
                    LocalDate.parse(ranobeModel.getAired_on(), DateFormat.HTMLshort.getFormat().get()).getYear(), ranobeModel.getId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }

    @Override
    @GetMapping("/ranobe/getByServiceId")
    public ResponseEntity<RanobeRs> getByIntegrationID(@RequestParam @NotNull int id) {
        var actualRanobe = ranobeCrud.findByShikiIdAndUserId(id);
        var ranobeModel = ShikimoriApi.ranobeApi().getById(id);
        var ranobe = new Ranobe(
                ranobeModel.getName(), ranobeModel.getRussian(), ranobeModel.getVolumes(),
                LocalDate.parse(ranobeModel.getAired_on(), DateFormat.HTMLshort.getFormat().get()), ranobeModel.getId()
        );
        return ResponseEntity.ok(
                new RanobeRs(Objects.requireNonNullElse(actualRanobe, ranobe), ranobeModel.getImage().getOriginal())
        );

    }

}
