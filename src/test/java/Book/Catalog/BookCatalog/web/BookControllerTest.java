package Book.Catalog.BookCatalog.web;

import Book.Catalog.BookCatalog.domain.Book;
import Book.Catalog.BookCatalog.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.*;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

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
}