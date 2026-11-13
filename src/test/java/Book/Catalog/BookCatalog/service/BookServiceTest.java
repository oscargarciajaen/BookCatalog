package Book.Catalog.BookCatalog.service;

import Book.Catalog.BookCatalog.domain.Book;
import Book.Catalog.BookCatalog.domain.BookCategory;
import Book.Catalog.BookCatalog.dto.BookDTO;
import Book.Catalog.BookCatalog.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
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
            BookDTO testBook = service.findById(1L);
            assertThat(testBook).isNotNull();
            assertThat(testBook.getTitulo()).isEqualTo("Game of Thrones");
        }
    }

    @Test
    void getAllBooks() {

        Book book1 = new Book(1L, "Game of Thrones", BookCategory.FANTASIA);
        Book book2 = new Book(2L, "Maze Runner", BookCategory.FANTASIA);
        Book book3 = new Book(3L, "Clean Code", BookCategory.TECNOLOGIA);

        List<Book> listaEntidades = List.of(book1, book2, book3);

        BookDTO bookDTO1 = new BookDTO(book1);
        BookDTO bookDTO2 = new BookDTO(book2);
        BookDTO bookDTO3 = new BookDTO(book3);

        List<BookDTO> listaEsperada = List.of(bookDTO1, bookDTO2, bookDTO3);

        when(repository.findAll()).thenReturn(listaEntidades);

        List<BookDTO> resultado = service.getAllBooks();

        assertThat(resultado)
                .isNotNull()
                .hasSize(3)
                .usingRecursiveFieldByFieldElementComparator()
                .isEqualTo(listaEsperada);
    }
}