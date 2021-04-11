package pl.coderslab.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.interfaces.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MemoryBookService implements BookService {

    private static final Logger log = LoggerFactory.getLogger(MemoryBookService.class);

    private List<Book> listOfBooks;
    private static Long nextId = 4L;

    @Override
    public void MockBookService(){
        //jeżeli robimy listę z list.of to ona jest NIEMUTOWALNA, ale możemy zrobić mutowalną w ten sposób
        log.info("Tworzenie listy książek");
        this.listOfBooks = new ArrayList<>(List.of(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"),
                new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion","programming"),
                new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion","programming")));

    }

    @Override
    public List<Book> getList() {
        log.debug("Pobieranie listy książek");
        return listOfBooks;
    }

    @Override
    public Optional<Book> getBook (Long id) throws ResponseStatusException {
        log.debug("Pobieranie książki o id: {}", id);
        return listOfBooks.stream()
                .filter(book -> book.getId().equals(id))
                .findAny();
    }

    @Override
    public void updateBook(Book book){
        Long id = book.getId();
        log.debug("Zmieniam książkę od id {}", id);

        listOfBooks.stream()
                .filter(b -> b.getId().equals(id))
                .map(b -> listOfBooks.indexOf(b))
                .findFirst()
                .ifPresentOrElse(index -> listOfBooks.set(index,book),
                        () -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"); });
    }

    @Override
    public void addBook(Book book){
        log.debug("Dodawanie książki {}", book);
        int sizeBefore = listOfBooks.size();
        book.setId(nextId);
        listOfBooks.add(book);
        log.debug("Długość listy zmieniła się o {}", listOfBooks.size() - sizeBefore);
        nextId++;
    }

    @Override
    public void deleteBook(Long id){
        log.debug("Usuwanie książki o id: {}", id);
        int sizeBefore = listOfBooks.size();

       listOfBooks.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(listOfBooks::remove,
                        ()->{throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");});
        log.debug("Długość listy zmieniła się o {}", listOfBooks.size() - sizeBefore);
    }
}
