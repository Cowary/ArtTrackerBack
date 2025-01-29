package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.manga.Manga;
import org.cowary.arttrackerback.rest.dto.MangaDto;

@UtilityClass
public class MangaDtoConverter {

    public static MangaDto convert(Manga manga) {
        return MangaDto.builder()
                .id(manga.getId())
                .originalTitle(manga.getOriginalTitle())
                .title(manga.getTitle())
                .volumes(manga.getVolumes())
                .chapters(manga.getChapters())
                .status(manga.getStatus())
                .score(manga.getScore())
                .releaseDate(manga.getReleaseDate())
                .releaseYear(manga.getReleaseYear())
                .endDate(manga.getEndDate())
                .shikiId(manga.getShikiId())
                .volumesEnd(manga.getVolumesEnd())
                .chaptersEnd(manga.getChaptersEnd())
                .build();
    }

    public static Manga convert(MangaDto mangaDto) {
        return Manga.builder()
                .id(mangaDto.getId())
                .originalTitle(mangaDto.getOriginalTitle())
                .title(mangaDto.getTitle())
                .volumes(mangaDto.getVolumes())
                .chapters(mangaDto.getChapters())
                .status(mangaDto.getStatus())
                .score(mangaDto.getScore())
                .releaseDate(mangaDto.getReleaseDate())
                .releaseYear(mangaDto.getReleaseYear())
                .endDate(mangaDto.getEndDate())
                .shikiId(mangaDto.getShikiId())
                .volumesEnd(mangaDto.getVolumesEnd())
                .chaptersEnd(mangaDto.getChaptersEnd())
                .build();
    }
}
