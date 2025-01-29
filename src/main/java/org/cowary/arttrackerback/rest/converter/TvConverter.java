package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.tv.Tv;
import org.cowary.arttrackerback.entity.tv.TvSeason;
import org.cowary.arttrackerback.rest.dto.TvDto;
import org.cowary.arttrackerback.rest.dto.TvSeasonDto;

@UtilityClass
public class TvConverter {

    public static Tv convert(TvDto tvDto) {
        return Tv.builder()
                .id(tvDto.getId())
                .originalTitle(tvDto.getOriginalTitle())
                .title(tvDto.getTitle())
                .releaseYear(tvDto.getReleaseYear())
                .score(tvDto.getScore())
                .seasons(tvDto.getSeasons())
                .usrId(tvDto.getUsrId())
                .integrationId(tvDto.getIntegrationId())
                .build();
    }

    public static TvDto convert(Tv tv) {
        return TvDto.builder()
                .id(tv.getId())
                .originalTitle(tv.getOriginalTitle())
                .title(tv.getTitle())
                .releaseYear(tv.getReleaseYear())
                .score(tv.getScore())
                .seasons(tv.getSeasons())
                .usrId(tv.getUsrId())
                .integrationId(tv.getIntegrationId())
                .build();
    }

    public static TvSeason convert(TvSeasonDto tvSeasonDto) {
        return TvSeason.builder()
                .id(tvSeasonDto.getId())
                .title(tvSeasonDto.getTitle())
                .number(tvSeasonDto.getNumber())
                .episodes(tvSeasonDto.getEpisodes())
                .episodesEnd(tvSeasonDto.getEpisodesEnd())
                .status(tvSeasonDto.getStatus())
                .score(tvSeasonDto.getScore())
                .endDate(tvSeasonDto.getEndDate())
                .tv(convert(tvSeasonDto.getTv()))
                .usrId(tvSeasonDto.getUsrId())
                .build();
    }

    public static TvSeasonDto convert(TvSeason tvSeason) {
        return TvSeasonDto.builder()
                .id(tvSeason.getId())
                .title(tvSeason.getTitle())
                .number(tvSeason.getNumber())
                .episodes(tvSeason.getEpisodes())
                .episodesEnd(tvSeason.getEpisodesEnd())
                .status(tvSeason.getStatus())
                .score(tvSeason.getScore())
                .endDate(tvSeason.getEndDate())
                .tv(convert(tvSeason.getTv()))
                .usrId(tvSeason.getUsrId())
                .build();
    }
}
