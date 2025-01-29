package org.cowary.arttrackerback.rest;

import jakarta.validation.Valid;
import lombok.Setter;
import org.cowary.arttrackerback.dbCase.book.BookCrud;
import org.cowary.arttrackerback.entity.book.Book;
import org.cowary.arttrackerback.rest.converter.BookDtoConverter;
import org.cowary.arttrackerback.rest.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
@Setter
public class BookController implements TitleController<BookDto> {

    @Autowired
    private BookCrud bookCrud;

    @Override
    @GetMapping("/book")
    public ResponseEntity<List<BookDto>> getAllByUsrId(@RequestHeader long userId) {
        var bookList = bookCrud.getAllByUserId(userId);
        var bookDtoList = bookList.stream().map(BookDtoConverter::convert).toList();
        return ResponseEntity.ok(
                bookDtoList
        );
    }

    @Override
    @GetMapping("/book/{titleId}")
    public ResponseEntity<BookDto> getTitle(@PathVariable long titleId) {
        var book = bookCrud.findById(titleId);
        var bookDto = BookDtoConverter.convert(book);
        return ResponseEntity.ok(
                bookDto
        );
    }

    @Override
    @PostMapping("/book")
    public ResponseEntity<BookDto> postTitle(@Valid @RequestBody BookDto title) {
        var book = BookDtoConverter.convert(title);
        bookCrud.save(book);
        title.setId(book.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    @PutMapping("/book")
    public ResponseEntity<BookDto> putTitle(@Valid @RequestBody BookDto title) {
        var book = BookDtoConverter.convert(title);
        bookCrud.save(book);
        return ResponseEntity.ok(title);
    }

    @Override
    @DeleteMapping("/book")
    public ResponseEntity<String> deleteTitle(@RequestHeader long id) {
        bookCrud.deleteById(id);
        return ResponseEntity.ok(String.format("book №%s deleted", id));
    }
}
