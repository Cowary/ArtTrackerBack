package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import org.cowary.arttrackerback.dbCase.anime.AnimeCrud;
import org.cowary.arttrackerback.dbCase.anime.AnimeRoleCrud;
import org.cowary.arttrackerback.dbCase.anime.AnimeStudioCrud;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.entity.api.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.api.findRs.Finds;
import org.cowary.arttrackerback.entity.api.mediaRs.AnimeRs;
import org.cowary.arttrackerback.integration.api.shiki.ShikimoriApi;
import org.cowary.arttrackerback.integration.model.shiki.AnimeModel;
import org.cowary.arttrackerback.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/title")
public class AnimeController implements TitleInterface<Anime>, FindController<AnimeRs> {

    @Autowired
    AnimeCrud animeCrud;
    @Autowired
    AnimeStudioCrud animeStudioCrud;
    @Autowired
    AnimeRoleCrud animeRoleCrud;

    @Override
    @GetMapping("/anime")
    public ResponseEntity<List<Anime>> getAllByUsrId(@RequestHeader long userId) {
        return ResponseEntity.ok(
                animeCrud.getAllByUserId(userId)
        );
    }

    @Override
    @GetMapping("/anime/{titleId}")
    public ResponseEntity<Anime> getTitle(@PathVariable long titleId) {
        return ResponseEntity.ok(
                animeCrud.getById(titleId)
        );
    }

    @Override
    @PostMapping("/anime")
    public ResponseEntity<Anime> postTitle(@Valid @RequestBody Anime title) {
        animeCrud.save(title);
        if (title.getShikiId() != null) {
            var animeModel = ShikimoriApi.animeApi().getById(title.getShikiId());
            var studioList = List.of(animeModel.getStudios());
            studioList.forEach(studioModel -> animeStudioCrud.create(studioModel.getName(), title.getId()));
            animeRoleCrud.create(title.getId(), animeModel.getRoleModels());
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    @PutMapping("/anime")
    public ResponseEntity<Anime> putTitle(@Valid @RequestBody Anime title) {
        animeCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    @DeleteMapping("/anime")
    public ResponseEntity<String> deleteTitle(@RequestHeader long id) {
        animeCrud.deleteById(id);

        return ResponseEntity.ok(String.format("anime №%s deleted", id));
    }

    @Override
    @GetMapping("/anime/find")
    public ResponseEntity<FindMediaRs> find(@RequestParam String keyword) {
        var animeModelList = ShikimoriApi.animeApi().searchByName(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (AnimeModel animeModel : animeModelList) {
            Integer airedDate = null;
            if (animeModel.getAired_on() != null) airedDate = DateFormat.HTMLshort.parse(animeModel.getAired_on()).getYear();
            var fins = new Finds(animeModel.getName(), animeModel.getRussian(), animeModel.getScore(), animeModel.getEpisodes(), airedDate, animeModel.getId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }

    @Override
    @GetMapping("/anime/getByServiceId")
    public ResponseEntity<AnimeRs> getByIntegrationID(@RequestParam int id) {
        var animeModel = ShikimoriApi.animeApi().getById(id);
        var anime = new Anime(
                animeModel.getName(), animeModel.getRussian(), animeModel.getEpisodes(), DateFormat.HTMLshort.parse(animeModel.getAired_on()), animeModel.getId(), animeModel.getDuration()
        );
        return ResponseEntity.ok(
                new AnimeRs(anime, animeModel.getImage().getOriginal())
        );
    }
}
