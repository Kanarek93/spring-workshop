package pl.coderslab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.service.BookService;
import pl.coderslab.model.Book;
import pl.coderslab.service.MemoryBookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    BookService bs;

    @Autowired
    public BookController(){
        this.bs = new MemoryBookService();
        bs.MockBookService();
    }

    @GetMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping("")
    public List<Book> getBooks(){
        return bs.getList();
    }

    @GetMapping("/{id:\\d+}")
    public Book getBook(@PathVariable Long id){
        return this.bs.getBook(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
        });
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book){
        bs.addBook(book);
    }

    @PutMapping("")
    public void updateBook(@RequestBody Book book){
        bs.updateBook(book);
    }

    @DeleteMapping("/{id:\\d+}")
    public void deleteBook(@PathVariable Long id){
        bs.deleteBook(id);
    }



}
