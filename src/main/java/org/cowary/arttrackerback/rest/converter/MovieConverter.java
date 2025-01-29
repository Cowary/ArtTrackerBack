package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.movie.Movie;
import org.cowary.arttrackerback.rest.dto.MovieDto;

@UtilityClass
public class MovieConverter {

    public static MovieDto convert(Movie movie) {
        return MovieDto.builder()
                .id(movie.getId())
                .originalTitle(movie.getOriginalTitle())
                .title(movie.getTitle())
                .releaseYear(movie.getReleaseYear())
                .duration(movie.getDuration())
                .status(movie.getStatus())
                .score(movie.getScore())
                .endDate(movie.getEndDate())
                .usrId(movie.getUsrId())
                .build();
    }

    public static Movie convert(MovieDto movieDto) {
        return Movie.builder()
                .id(movieDto.getId())
                .originalTitle(movieDto.getOriginalTitle())
                .title(movieDto.getTitle())
                .releaseYear(movieDto.getReleaseYear())
                .duration(movieDto.getDuration())
                .status(movieDto.getStatus())
                .score(movieDto.getScore())
                .endDate(movieDto.getEndDate())
                .usrId(movieDto.getUsrId())
                .build();
    }

}
