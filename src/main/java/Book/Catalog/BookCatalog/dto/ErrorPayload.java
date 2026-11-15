package Book.Catalog.BookCatalog.dto;

import java.time.Instant;

public record ErrorPayload(String error, String message, String timestamp) {
    public static ErrorPayload of(String error, String message) {
        return new ErrorPayload(error, message, Instant.now().toString());
    }
}