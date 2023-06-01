package org.cowary.arttrackerback.rest;

import org.cowary.arttrackerback.dbCase.book.BookCrud;
import org.cowary.arttrackerback.entity.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/title")
public class BookController implements TitleImpl<Book> {

    @Autowired
    BookCrud bookCrud;

    @Override
    @GetMapping("/book")
    public List<Book> getAllByUsrId(@RequestHeader long userId) {
        return bookCrud.getAllByUserId(userId);
    }

    @Override
    @GetMapping("/book/{titleId}")
    public Book getTitle(@PathVariable long titleId) {
        return bookCrud.findById(titleId);
    }

    @Override
    public ResponseEntity<Book> postTitle(Book title) {
        return null;
    }

    @Override
    public ResponseEntity<Book> putTitle(Book title) {
        return null;
    }

    @Override
    public ResponseEntity<String> deleteTitle(long id) {
        return null;
    }
}
