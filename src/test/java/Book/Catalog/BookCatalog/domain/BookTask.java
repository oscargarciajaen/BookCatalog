package Book.Catalog.BookCatalog.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookTask {

    @Test
    void newBookTest(){
        var book = new Book("Game of thrones", 509);
        assertThat(book.isBook()).isTrue();
    }
}
