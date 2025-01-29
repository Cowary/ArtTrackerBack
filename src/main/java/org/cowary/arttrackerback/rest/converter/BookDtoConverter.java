package org.cowary.arttrackerback.rest.converter;

import lombok.experimental.UtilityClass;
import org.cowary.arttrackerback.entity.book.Book;
import org.cowary.arttrackerback.rest.dto.BookDto;

@UtilityClass
public class BookDtoConverter {

    public static BookDto convert(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .endDate(book.getEndDate())
                .releaseDate(book.getReleaseDate())
                .releaseYear(book.getReleaseYear())
                .score(book.getScore())
                .status(book.getStatus())
                .author(book.getAuthor())
                .usrId(book.getUsrId())
                .build();
    }

    public static Book convert(BookDto dto) {
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .endDate(dto.getEndDate())
                .releaseDate(dto.getReleaseDate())
                .releaseYear(dto.getReleaseYear())
                .score(dto.getScore())
                .status(dto.getStatus())
                .author(dto.getAuthor())
                .usrId(dto.getUsrId())
                .build();
    }
}
