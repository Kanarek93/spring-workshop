package pl.coderslab.entity;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MemoryBookService {

    private List<Book> listOfBooks;
    private static  Long nextId = 4L;

    public void MockBookService(){
        this.listOfBooks = List.of(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"),
                new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion","programming"),
                new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion","programming"));
    }
}
