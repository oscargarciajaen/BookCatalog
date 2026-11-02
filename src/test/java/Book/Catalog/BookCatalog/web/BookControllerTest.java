package Book.Catalog.BookCatalog.web;

import Book.Catalog.BookCatalog.domain.Book;
import Book.Catalog.BookCatalog.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean; // O org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
}