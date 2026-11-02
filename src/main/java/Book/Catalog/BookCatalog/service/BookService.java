package Book.Catalog.BookCatalog.service;

import Book.Catalog.BookCatalog.domain.Book;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    public Book findById(Long id) {
        Book book = new Book("Game of thrones", 500);
        book.setId(id);
        return book;
    }
}