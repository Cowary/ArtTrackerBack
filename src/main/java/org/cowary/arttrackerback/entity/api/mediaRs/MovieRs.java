package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cowary.arttrackerback.entity.movie.Movie;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRs implements MediaRs {

    private Movie movie;
    private String posterUrl;
}
