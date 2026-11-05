package Book.Catalog.BookCatalog.dto;

import Book.Catalog.BookCatalog.domain.Book;
import Book.Catalog.BookCatalog.domain.BookCategory;

public class BookDTO {

    private Long id;
    private String titulo;
    private BookCategory categoria;

    public BookDTO(Book book) {
        this.id = book.getId();
        this.titulo = book.getTitulo();
        this.categoria = book.getCategory();
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public BookCategory getCategoria() {
        return categoria;
    }
}