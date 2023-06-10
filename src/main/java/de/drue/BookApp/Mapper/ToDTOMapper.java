package de.drue.BookApp.Mapper;

import de.drue.BookApp.DTO.BookDTO;
import de.drue.BookApp.Entity.Book;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class ToDTOMapper implements Function<Book, BookDTO> {
    @Override
    public BookDTO apply(final Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getPublisher()
        );
    }
}
