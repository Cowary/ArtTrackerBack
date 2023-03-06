package org.cowary.arttrackerback.dbCase.book;

import org.cowary.arttrackerback.dbCase.UserService;
import org.cowary.arttrackerback.entity.book.Book;
import org.cowary.arttrackerback.repo.book.BookRepo;
import org.cowary.arttrackerback.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookCrud {

    @Autowired
    BookRepo bookRepo;
    @Autowired
    UserService userService;

    public List<Book> getAll(String status) {
        long userId = userService.getIdCurrentUser();
        if(status.equals("")) return bookRepo.findAllByUsrId(userId);
        else return bookRepo.findByStatus(status);
    }

    public List<Book> getAllByUserId(long userId) {
        return bookRepo.findAllByUsrId(userId);
    }

    public Book findById(long id) {
        return bookRepo.findById(id).orElseThrow();
    }

    public void save(Book book) {
        book.setLastUpd(DateUtil.now());
        book.setUsrId(userService.getIdCurrentUser());
        bookRepo.save(book);
    }

    public void delete(Book book) {
        bookRepo.delete(book);
    }
}
