package pl.coderslab.interfaces;

import org.springframework.stereotype.Component;
import pl.coderslab.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void MockBookService();
    List<Book> getList();
    Optional<Book> getBook(Long id);
    void addBook(Book book);
    void deleteBook(Long id);
    void updateBook(Book book);
}
