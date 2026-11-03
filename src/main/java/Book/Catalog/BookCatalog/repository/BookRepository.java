package Book.Catalog.BookCatalog.repository;

import Book.Catalog.BookCatalog.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
