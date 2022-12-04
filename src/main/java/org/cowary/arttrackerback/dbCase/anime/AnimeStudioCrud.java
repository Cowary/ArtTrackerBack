package org.cowary.arttrackerback.dbCase.anime;

import org.cowary.arttrackerback.dbCase.StudioCrud;
import org.cowary.arttrackerback.entity.Studio;
import org.cowary.arttrackerback.entity.anime.AnimeStudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.anime.AnimeStudioRep;

import java.util.List;

@Component
public class AnimeStudioCrud {

    @Autowired
    AnimeStudioRep animeStudioRep;
    @Autowired
    StudioCrud studioCrud;

    public void create(String studioName, Long animeId) {
        Studio studio = studioCrud.createOrGetByName(studioName);
        AnimeStudio animeStudio = new AnimeStudio(animeId, studio.getId());
        animeStudioRep.save(animeStudio);
    }

    public List<AnimeStudio> findByAnimeId(Long animeId) {
        return animeStudioRep.findByAnimeId(animeId);
    }
}
