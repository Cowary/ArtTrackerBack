package org.cowary.arttrackerback.dbCase;


import org.cowary.arttrackerback.entity.Studio;
import org.cowary.arttrackerback.entity.anime.AnimeStudio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.StudioRep;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudioCrud {

    @Autowired
    StudioRep studioRep;

    public Studio createOrGetByName(String name) {
        return studioRep.findByName(name)
                .orElseGet(() -> {
                    Studio studio = new Studio(name);
                    studioRep.save(studio);
                    return studio;
        });
    }

    public Studio findById(Long id) {
        return studioRep.findById(id).orElseThrow();
    }

    public List<Studio> findById(List<AnimeStudio> longList) {
        return longList.stream()
                .map(lg -> studioRep.findById(lg.getStudioId()).orElseThrow())
                .collect(Collectors.toList());
    }
}
