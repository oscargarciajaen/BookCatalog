package Book.Catalog.BookCatalog.service;

import Book.Catalog.BookCatalog.domain.Book;
import Book.Catalog.BookCatalog.domain.BookCategory;
import Book.Catalog.BookCatalog.domain.exceptions.BookNotFoundException;
import Book.Catalog.BookCatalog.dto.BookDTO;
import Book.Catalog.BookCatalog.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException("Libro no encontrado"));
    }

    public Book save(Book book) {
        repository.save(book);
        return book;
    }

    public List<BookDTO> getBookByCategory(BookCategory categoria) {

        return repository.findByCategoria(categoria)
                .stream()
                .map(BookDTO::new)
                .toList();
    }

    public List<BookDTO> getAllBooks() {

        return repository.findAll()
                .stream()
                .map(BookDTO::new)
                .toList();
    }
}