package Book.Catalog.BookCatalog.service;

import Book.Catalog.BookCatalog.domain.Book;
import Book.Catalog.BookCatalog.domain.exceptions.BookNotFoundException;
import Book.Catalog.BookCatalog.repository.BookRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class BookService {

    BookRepository repository;

    public BookService(BookRepository repository){
        this.repository = repository;
    }

    public Book findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BookNotFoundException("Libro no encontrado"));
    }

    public Book save(Book book){
        repository.save(book);
        return book;
    }
}