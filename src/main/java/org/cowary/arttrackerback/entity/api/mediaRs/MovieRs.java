package org.cowary.arttrackerback.entity.api.mediaRs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cowary.arttrackerback.entity.movie.Movie;
import org.cowary.arttrackerback.rest.dto.MovieDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRs implements MediaRs {

    private MovieDto movie;
    private String posterUrl;
}
