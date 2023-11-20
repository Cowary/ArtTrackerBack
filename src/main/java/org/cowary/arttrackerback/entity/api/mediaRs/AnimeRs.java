package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cowary.arttrackerback.entity.anime.Anime;

@Data
@AllArgsConstructor
public class AnimeRs implements MediaRs {

    private Anime media;
    private String posterUrl;
}
