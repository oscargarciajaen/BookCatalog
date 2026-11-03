package Book.Catalog.BookCatalog.service;

import Book.Catalog.BookCatalog.domain.Book;
import Book.Catalog.BookCatalog.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository repository;
    @InjectMocks
    BookService service;


    @Test
    void getBookById() {
        Book book = new Book();
        book.setTitulo("Game of Thrones");
        when(repository.findById(1L)).thenReturn(Optional.of(book));

        if (service.findById(1L) != null){
            Book testBook = service.findById(1L);
            assertThat(testBook).isNotNull();
            assertThat(testBook.getTitulo()).isEqualTo("Game of Thrones");
        }
    }
}