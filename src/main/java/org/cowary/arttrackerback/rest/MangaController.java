package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import org.cowary.arttrackerback.dbCase.manga.MangaCrud;
import org.cowary.arttrackerback.dbCase.manga.MangaPublisherCrud;
import org.cowary.arttrackerback.dbCase.manga.MangaRoleCrud;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/title")
public class MangaController implements TitleInterface<Manga>, FindController {

    @Autowired
    private MangaCrud mangaCrud;
    @Autowired
    private MangaPublisherCrud mangaPublisherCrud;
    @Autowired
    private MangaRoleCrud mangaRoleCrud;

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
                mangaCrud.findByUserId(titleId)
        );
    }

    @Override
    @PostMapping("/manga")
    public ResponseEntity<Manga> postTitle(@Valid @RequestBody Manga title) {
        mangaCrud.save(title);
        if (title.getShikiId() != null) {
            var mangaModel = ShikimoriApi.mangaApi().getById(title.getShikiId());
            mangaPublisherCrud.create(title.getId(), List.of(mangaModel.getPublishers()));
            mangaRoleCrud.create(title.getId(), List.of(mangaModel.getRoleModels()));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    @PutMapping("/manga")
    public ResponseEntity<Manga> putTitle(Manga title) {
        mangaCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    @DeleteMapping("/manga")
    public ResponseEntity<String> deleteTitle(long id) {
        mangaCrud.deleteById(id);
        return ResponseEntity.ok(String.format("Manga №%s deleted", id));
    }

    @Override
    @GetMapping("/manga/find")
    public ResponseEntity<FindMediaRs> find(@RequestParam(required = false) String keyword) {
        System.out.println("keyword: " + keyword);
        Objects.requireNonNull(keyword);
        var mediaModelList = ShikimoriApi.mangaApi().searchByName(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (MangaModel mangaModel : mediaModelList) {
            var fins = new Finds(mangaModel.getName(), mangaModel.getRussian(), mangaModel.getScore(), mangaModel.getChapters(), DateFormat.HTMLshort.parse(mangaModel.getAired_on()).getYear(), mangaModel.getId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }

    @Override
    @GetMapping("/manga/getByServiceId")
    public ResponseEntity<MangaRs> getByIntegrationID(@RequestParam int id) {
        var mangaModel = ShikimoriApi.mangaApi().getById(id);
        var manga = new Manga(
                mangaModel.getName(), mangaModel.getRussian(), mangaModel.getVolumes(), mangaModel.getChapters(), DateFormat.HTMLshort.parse(mangaModel.getAired_on()), mangaModel.getId()
        );
        return ResponseEntity.ok(
                new MangaRs(manga, mangaModel.getImage().getOriginal())
        );
    }
}
