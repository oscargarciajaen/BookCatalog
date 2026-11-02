package Book.Catalog.BookCatalog.service;

import Book.Catalog.BookCatalog.domain.Book;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookServiceTest {

    @Test
    void getBookById() {
        BookService bookService = new BookService();
        Book book = bookService.findById(1L);

        assertThat(book).isNotNull();
        assertThat(book.getTitulo()).isEqualTo("Game of thrones");
    }
}