package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.manga.MangaCrud;
import org.cowary.arttrackerback.entity.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.findRs.Finds;
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

@RestController
@RequestMapping("/title")
public class MangaController implements TitleInterface<Manga>, FindController {

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
                mangaCrud.findByUserId(titleId)
        );
    }

    @Override
    public ResponseEntity<Manga> postTitle(Manga title) {
        mangaCrud.save(title);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    public ResponseEntity<Manga> putTitle(Manga title) {
        mangaCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    public ResponseEntity<String> deleteTitle(long id) {
        mangaCrud.deleteById(id);
        return ResponseEntity.ok(String.format("Manga №%s deleted", id));
    }

    @Override
    @GetMapping("/manga/find")
    public ResponseEntity<FindMediaRs> find(String keyword) {
        var mediaModelList = ShikimoriApi.mangaApi().searchByName(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (MangaModel mangaModel : mediaModelList) {
            var fins = new Finds(mangaModel.getName(), mangaModel.getRussian(), mangaModel.getScore(), mangaModel.getChapters(), DateFormat.HTMLshort.parse(mangaModel.getAired_on()).getYear(), mangaModel.getId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }

    @Override
    public ResponseEntity getByIntegrationID(int id) {
        return null;
    }
}
