package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;
import org.cowary.arttrackerback.dbCase.anime.AnimeCrud;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.entity.api.findRs.FindMediaRs;
import org.cowary.arttrackerback.entity.api.findRs.Finds;
import org.cowary.arttrackerback.entity.api.mediaRs.AnimeRs;
import org.cowary.arttrackerback.integration.api.shiki.ShikimoriApi;
import org.cowary.arttrackerback.integration.model.shiki.AnimeModel;
import org.cowary.arttrackerback.rest.converter.AnimeDtoConverter;
import org.cowary.arttrackerback.rest.dto.AnimeDto;
import org.cowary.arttrackerback.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/title")
@Setter
public class AnimeController implements TitleController<AnimeDto>, FindController<AnimeRs> {

    @Autowired
    private AnimeCrud animeCrud;

    @Override
    @GetMapping("/anime")
    public ResponseEntity<List<AnimeDto>> getAllByUsrId(@RequestHeader long userId) {
        var animeList = animeCrud.getAllByUserId(userId);
        var animeDtoList = animeList.stream().map(AnimeDtoConverter::convert).toList();
        return ResponseEntity.ok(
                animeDtoList
        );
    }

    @Override
    @GetMapping("/anime/{titleId}")
    public ResponseEntity<AnimeDto> getTitle(@PathVariable long titleId) {
        var anime = animeCrud.getById(titleId);
        var animeDto = AnimeDtoConverter.convert(anime);
        return ResponseEntity.ok(
                animeDto
        );
    }

    @Override
    @PostMapping("/anime")
    public ResponseEntity<AnimeDto> postTitle(@Valid @RequestBody AnimeDto title) {
        var anime = AnimeDtoConverter.convert(title);
        animeCrud.save(anime);
        title.setId(anime.getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    @PutMapping("/anime")
    public ResponseEntity<AnimeDto> putTitle(@Valid @RequestBody AnimeDto title) {
        var anime = AnimeDtoConverter.convert(title);
        animeCrud.save(anime);
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
    public ResponseEntity<FindMediaRs> find(@RequestParam @NotBlank String keyword) {
        var animeModelList = ShikimoriApi.animeApi().searchByName(keyword);
        List<Finds> findsList = new ArrayList<>();
        for (AnimeModel animeModel : animeModelList) {
            var releaseDate = animeModel.getAired_on() == null ? 0 : LocalDate.parse(animeModel.getAired_on(), DateFormat.HTMLshort.getFormat().get()).getYear();
            var fins = new Finds(animeModel.getName(), animeModel.getRussian(), animeModel.getScore(), animeModel.getEpisodes(),
                    releaseDate, animeModel.getId());
            findsList.add(fins);
        }
        return ResponseEntity.ok(new FindMediaRs(findsList));
    }

    @Override
    @GetMapping("/anime/getByServiceId")
    public ResponseEntity<AnimeRs> getByIntegrationID(@RequestParam @NotNull int id) {
        //TODO: переделать
        var animeModel = ShikimoriApi.animeApi().getById(id);
        var anime = new Anime(
                animeModel.getName(), animeModel.getRussian(), animeModel.getEpisodes(), DateFormat.HTMLshort.parse(animeModel.getAired_on()), animeModel.getId(), animeModel.getDuration()
        );
        var animeDto = AnimeDtoConverter.convert(anime);
        return ResponseEntity.ok(
                new AnimeRs(animeDto, animeModel.getImage().getOriginal())
        );
    }
}
