package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.book.BookCrud;
import org.cowary.arttrackerback.entity.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class BookController implements TitleInterface<Book> {

    @Autowired
    BookCrud bookCrud;

    @Override
    @GetMapping("/book")
    public ResponseEntity<List<Book>> getAllByUsrId(@RequestHeader long userId) {
        return ResponseEntity.ok(
                bookCrud.getAllByUserId(userId)
        );
    }

    @Override
    @GetMapping("/book/{titleId}")
    public ResponseEntity<Book> getTitle(@PathVariable long titleId) {
        return ResponseEntity.ok(
                bookCrud.findById(titleId)
        );
    }

    @Override
    public ResponseEntity<Book> postTitle(Book title) {
        bookCrud.save(title);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(title);
    }

    @Override
    public ResponseEntity<Book> putTitle(Book title) {
        bookCrud.save(title);
        return ResponseEntity.ok(title);
    }

    @Override
    public ResponseEntity<String> deleteTitle(long id) {
        bookCrud.deleteById(id);
        return ResponseEntity.ok(String.format("book №%s deleted", id));
    }
}
