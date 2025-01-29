package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.rest.dto.AnimeDto;

@Data
@AllArgsConstructor
public class AnimeRs implements MediaRs {

    private AnimeDto media;
    private String posterUrl;
}
