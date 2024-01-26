package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.cowary.arttrackerback.dbCase.manga.MangaCrud;
import org.cowary.arttrackerback.entity.api.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.api.findRs.Finds;
import org.cowary.arttrackerback.entity.api.mediaRs.MangaRs;
import org.cowary.arttrackerback.entity.manga.Manga;
import org.cowary.arttrackerback.integration.api.shiki.ShikimoriApi;
import org.cowary.arttrackerback.integration.model.shiki.MangaModel;
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
public class MangaController implements TitleInterface<Manga>, FindController<MangaRs> {

    @Autowired
    private MangaCrud mangaCrud;

    @Override
    @GetMapping("/manga")
    public ResponseEntity<List<Manga>> getAllByUsrId(@RequestHeader long userId) {
        return ResponseEntity.ok(
                mangaCrud.findAllByUserId(userId)
        );
    }

    @Override
    @GetMapping("/manga/{titleId}")
    public ResponseEntity<Manga> getTitle(@PathVariable long titleId) {
        return ResponseEntity.ok(
                mangaCrud.findById(titleId)
        );
    }

    @Override
    @PostMapping("/manga")
    public ResponseEntity<Manga> postTitle(@RequestBody @Valid Manga title) {
        mangaCrud.save(title);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    @PutMapping("/manga")
    public ResponseEntity<Manga> putTitle(@RequestBody @Valid Manga title) {
        mangaCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    @DeleteMapping("/manga")
    public ResponseEntity<String> deleteTitle(@RequestHeader @NotNull long id) {
        mangaCrud.deleteById(id);
        return ResponseEntity.ok(String.format("Manga №%s deleted", id));
    }

    @Override
    @GetMapping("/manga/find")
    public ResponseEntity<FindMediaRs> find(@RequestParam @NotBlank String keyword) {
        System.out.println("keyword: " + keyword);
        Objects.requireNonNull(keyword);
        var mediaModelList = ShikimoriApi.mangaApi().searchByName(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (MangaModel mangaModel : mediaModelList) {
            var releaseDate = mangaModel.getAired_on() == null ? null : LocalDate.parse(mangaModel.getAired_on(), DateFormat.HTMLshort.getFormat().get()).getYear();
            var fins = new Finds(mangaModel.getName(), mangaModel.getRussian(), mangaModel.getScore(), mangaModel.getChapters(),
                    releaseDate, mangaModel.getId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }

    @Override
    @GetMapping("/manga/getByServiceId")
    public ResponseEntity<MangaRs> getByIntegrationID(@RequestParam @NotNull int id) {
        var mangaModel = ShikimoriApi.mangaApi().getById(id);
        var manga = new Manga(
                mangaModel.getName(), mangaModel.getRussian(), mangaModel.getVolumes(), mangaModel.getChapters(), DateFormat.HTMLshort.parse(mangaModel.getAired_on()), mangaModel.getId()
        );
        return ResponseEntity.ok(
                new MangaRs(manga, mangaModel.getImage().getOriginal())
        );
    }
}
