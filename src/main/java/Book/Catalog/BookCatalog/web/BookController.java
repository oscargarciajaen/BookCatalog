package Book.Catalog.BookCatalog.web;

import Book.Catalog.BookCatalog.domain.Book;
import Book.Catalog.BookCatalog.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        Book guardado = bookService.save(book);
        return ResponseEntity.created(URI.create("/books/" + guardado.getId())).body(guardado);
    }
}