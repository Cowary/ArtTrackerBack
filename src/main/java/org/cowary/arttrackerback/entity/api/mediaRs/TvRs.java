package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.cowary.arttrackerback.entity.tv.Tv;

@Data
@AllArgsConstructor
public class TvRs implements MediaRs {

    private Tv tv;
    private String posterUrl;
}
