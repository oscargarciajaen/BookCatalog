package Book.Catalog.BookCatalog.dto;

import Book.Catalog.BookCatalog.domain.Book;
import Book.Catalog.BookCatalog.domain.BookCategory;

public record BookDTO(Long id, String title, BookCategory category) {
    public BookDTO(Book book) {
        this(book.getId(), book.getTitulo(), book.getCategory());
    }

    public String getTitulo(){
        return this.title;
    }
}