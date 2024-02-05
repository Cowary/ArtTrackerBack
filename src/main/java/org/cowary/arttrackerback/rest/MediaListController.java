package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.anime.AnimeCrud;
import org.cowary.arttrackerback.dbCase.book.BookCrud;
import org.cowary.arttrackerback.dbCase.game.GameCrud;
import org.cowary.arttrackerback.dbCase.manga.MangaCrud;
import org.cowary.arttrackerback.dbCase.movie.MovieCrud;
import org.cowary.arttrackerback.dbCase.ranobe.RanobeVolumeCrud;
import org.cowary.arttrackerback.dbCase.tv.TvSeasonsCrud;
import org.cowary.arttrackerback.entity.Media;
import org.cowary.arttrackerback.entity.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/title/view")
public class MediaListController {

    @Autowired
    AnimeCrud animeCrud;
    @Autowired
    MangaCrud mangaCrud;
    @Autowired
    RanobeVolumeCrud ranobeVolumeCrud;
    @Autowired
    MovieCrud movieCrud;
    @Autowired
    GameCrud gameCrud;
    @Autowired
    BookCrud bookCrud;
    @Autowired
    TvSeasonsCrud tvSeasonsCrud;

    @GetMapping("/media")
    public ResponseEntity<List<Media>> getMediaList(@RequestHeader long userId,
                                                    @RequestParam(required = false, defaultValue = "") String status
    ) {
        List<Media> mediaList = new ArrayList<>();
        mediaList.addAll(animeCrud.getAll(userId, status));
        mediaList.addAll(mangaCrud.getAll(userId, status));
        mediaList.addAll(ranobeVolumeCrud.getAll(userId, status));
        mediaList.addAll(movieCrud.getAll(userId, status));
        mediaList.addAll(gameCrud.getAll(userId, status));
        mediaList.addAll(bookCrud.getAll(userId, status));
        mediaList.addAll(tvSeasonsCrud.getAll(userId, status));

        mediaList = mediaList.stream()
                .sorted((o1, o2) -> new Media().getComparator().compare(o1, o2))
                .collect(Collectors.toList());
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/play")
    public ResponseEntity<List<Game>> getPlayList(@RequestHeader long userId,
                                                  @RequestParam(required = false, defaultValue = "") String status) {
        return ResponseEntity.ok(
                gameCrud.getAll(userId, status).stream()
                        .sorted((o1, o2) -> new Media().getComparator().compare(o1, o2))
                        .toList());
    }

    @GetMapping("/read")
    public ResponseEntity<List<Media>> getReadList(@RequestHeader long userId,
                                                   @RequestParam(required = false, defaultValue = "") String status) {
        List<Media> mediaList = new ArrayList<>();
        mediaList.addAll(mangaCrud.getAll(userId, status));
        mediaList.addAll(ranobeVolumeCrud.getAll(userId, status));
        mediaList.addAll(bookCrud.getAll(userId, status));

        mediaList = mediaList.stream()
                .sorted((o1, o2) -> new Media().getComparator().compare(o1, o2))
                .collect(Collectors.toList());
        return ResponseEntity.ok(mediaList);
    }

    @GetMapping("/watch")
    public ResponseEntity<List<Media>> getWatchList(@RequestHeader long userId,
                                                    @RequestParam(required = false, defaultValue = "") String status) {
        List<Media> mediaList = new ArrayList<>();
        mediaList.addAll(animeCrud.getAll(userId, status));
        mediaList.addAll(movieCrud.getAll(userId, status));
        mediaList.addAll(tvSeasonsCrud.getAll(userId, status));

        mediaList = mediaList.stream()
                .sorted((o1, o2) -> new Media().getComparator().compare(o1, o2))
                .collect(Collectors.toList());
        return ResponseEntity.ok(mediaList);
    }
}
