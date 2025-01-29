package org.cowary.arttrackerback.rest.converter;

import jakarta.validation.constraints.NotNull;
import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.anime.Anime;
import org.cowary.arttrackerback.rest.dto.AnimeDto;

@UtilityClass
public class AnimeDtoConverter {

    public AnimeDto convert(@NotNull Anime anime) {
        return AnimeDto.builder()
                .id(anime.getId())
                .originalTitle(anime.getOriginalTitle())
                .title(anime.getTitle())
                .episodes(anime.getEpisodes())
                .status(anime.getStatus())
                .score(anime.getScore())
                .endDate(anime.getEndDate())
                .releaseDate(anime.getReleaseDate())
                .releaseYear(anime.getReleaseYear())
                .duration(anime.getDuration())
                .episodesEnd(anime.getEpisodesEnd())
                .usrId(anime.getUsrId())
                .build();
    }

    public Anime convert(@NotNull AnimeDto animeDto) {
        return Anime.builder()
                .id(animeDto.getId())
                .originalTitle(animeDto.getOriginalTitle())
                .title(animeDto.getTitle())
                .episodes(animeDto.getEpisodes())
                .status(animeDto.getStatus())
                .score(animeDto.getScore())
                .endDate(animeDto.getEndDate())
                .releaseDate(animeDto.getReleaseDate())
                .releaseYear(animeDto.getReleaseYear())
                .shikiId(animeDto.getShikiId())
                .duration(animeDto.getDuration())
                .episodesEnd(animeDto.getEpisodesEnd())
                .usrId(animeDto.getUsrId())
                .build();
    }
}
