package Book.Catalog.BookCatalog.web;

import Book.Catalog.BookCatalog.domain.Book;
import Book.Catalog.BookCatalog.domain.BookCategory;
import Book.Catalog.BookCatalog.dto.BookDTO;
import Book.Catalog.BookCatalog.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.*;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private BookService bookService;

    @Test
    void getBookById_ShouldReturnBookAndOkStatus() throws Exception {
        Book sampleBook = new Book("Game of thrones", 500);
        sampleBook.setId(1L);
        given(bookService.findById(1L)).willReturn(sampleBook);

        mockMvc.perform(get("/books/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Game of thrones"));
    }

    @Test
    void createBook_ShouldReturnCreatedStatusAndLocationHeader() throws Exception {
        Book inputBook = new Book("New Book", 300);
        Book savedBook = new Book("New Book", 300);
        savedBook.setId(99L);

        given(bookService.save(any(Book.class))).willReturn(savedBook);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputBook)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/books/99"))
                .andExpect(jsonPath("$.id").value(99));
    }

    @Test
    void getBookByCategory_ShouldReturnListDtoBooks() throws Exception {

        Book book1 = new Book(1L, "Spring Boot en Acción", BookCategory.TECNOLOGIA);

        Book book2 = new Book(2L, "Clean Code", BookCategory.TECNOLOGIA);

        BookDTO dto1 = new BookDTO(book1);
        BookDTO dto2 = new BookDTO(book2);

        given(bookService.getBookByCategory(BookCategory.TECNOLOGIA))
                .willReturn(Arrays.asList(dto1, dto2));

        mockMvc.perform(get("/books/category/TECNOLOGIA")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        hasSize(2)))
                .andExpect(jsonPath("$[0].titulo")
                        .value("Spring Boot en Acción"))
                .andExpect(jsonPath("$[0].categoria")
                        .value("TECNOLOGIA"))
                .andExpect(jsonPath("$[1].titulo")
                        .value("Clean Code"));
    }

    @Test
    void getAllBooks_ShouldReturnListDtoBooks() throws Exception {

        Book book1 = new Book(1L, "Spring Boot en Acción", BookCategory.TECNOLOGIA);
        Book book2 = new Book(2L, "Clean Code", BookCategory.TECNOLOGIA);

        BookDTO dto1 = new BookDTO(book1);
        BookDTO dto2 = new BookDTO(book2);

        given(bookService.getAllBooks())
                .willReturn(Arrays.asList(dto1, dto2));

        mockMvc.perform(get("/books")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        org.hamcrest.Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].title")
                        .value("Spring Boot en Acción"))
                .andExpect(jsonPath("$[0].category")
                        .value("TECNOLOGIA"))
                .andExpect(jsonPath("$[1].title")
                        .value("Clean Code"))
                .andExpect(jsonPath("$[1].category")
                        .value("TECNOLOGIA"));
    }
}