package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cowary.arttrackerback.entity.tv.Tv;
import org.cowary.arttrackerback.rest.dto.TvDto;
import org.cowary.arttrackerback.rest.dto.TvSeasonDto;

@Data
@AllArgsConstructor
public class TvRs implements MediaRs {

    private TvDto tv;
    private String posterUrl;
}
