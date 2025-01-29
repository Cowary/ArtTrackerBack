package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cowary.arttrackerback.entity.manga.Manga;
import org.cowary.arttrackerback.rest.dto.MangaDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MangaRs implements MediaRs {

    private MangaDto manga;
    private String posterUrl;
}
