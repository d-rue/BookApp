package de.drue.BookApp.Mapper;

import de.drue.BookApp.DTO.BookDTO;
import de.drue.BookApp.Entity.Book;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ToEntityMapper implements Function<BookDTO, Book> {
    @Override
    public Book apply(final BookDTO bookDTO) {
        return new Book(
                bookDTO.id(),
                bookDTO.title(),
                bookDTO.author(),
                bookDTO.genre(),
                bookDTO.publisher()
        );
    }
}
