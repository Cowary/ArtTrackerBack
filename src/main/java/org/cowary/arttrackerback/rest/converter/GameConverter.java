package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.game.Game;
import org.cowary.arttrackerback.rest.dto.GameDto;

@UtilityClass
public class GameConverter {

    public static GameDto convert(Game game) {
        return GameDto.builder()
                .id(game.getId())
                .title(game.getTitle())
                .endDate(game.getEndDate())
                .releaseDate(game.getReleaseDate())
                .releaseYear(game.getReleaseYear())
                .score(game.getScore())
                .status(game.getStatus())
                .usrId(game.getUsrId())
                .build();
    }

    public static Game convert(GameDto gameDto) {
        return Game.builder()
                .id(gameDto.getId())
                .title(gameDto.getTitle())
                .endDate(gameDto.getEndDate())
                .releaseDate(gameDto.getReleaseDate())
                .releaseYear(gameDto.getReleaseYear())
                .score(gameDto.getScore())
                .status(gameDto.getStatus())
                .usrId(gameDto.getUsrId())
                .build();
    }
}
