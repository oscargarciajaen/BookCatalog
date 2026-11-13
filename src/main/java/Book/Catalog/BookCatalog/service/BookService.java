package Book.Catalog.BookCatalog.service;

import Book.Catalog.BookCatalog.domain.Book;
import Book.Catalog.BookCatalog.domain.BookCategory;
import Book.Catalog.BookCatalog.domain.exceptions.BookNotFoundException;
import Book.Catalog.BookCatalog.dto.BookDTO;
import Book.Catalog.BookCatalog.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public BookDTO findById(Long id) {
        Optional<Book> bookOptional = repository.findById(id);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            return new BookDTO(book);
        } else {
            throw new BookNotFoundException("Libro no encontrado");
        }
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