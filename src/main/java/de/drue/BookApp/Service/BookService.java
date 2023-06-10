package de.drue.BookApp.Service;

import de.drue.BookApp.DTO.BookDTO;
import de.drue.BookApp.Exception.ResourceInappropriateException;
import de.drue.BookApp.Exception.ResourceNotFoundException;
import de.drue.BookApp.Mapper.ToDTOMapper;
import de.drue.BookApp.Mapper.ToEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import de.drue.BookApp.Entity.Book;
import de.drue.BookApp.Repository.BookRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ToDTOMapper toDTOMapper;
    private final ToEntityMapper toEntityMapper;

    public List<BookDTO> getAllBooks(){
        return bookRepository.findAll()
                .stream()
                .map(toDTOMapper)
                .collect(Collectors.toList());
    }

    public BookDTO getOneBookById(long id) {
        return bookRepository.findById(id)
                .map(toDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException("ID not found: " + id));
    }

    public Long removeOneBook(long id) {
        bookRepository.deleteById(id);
        return id;
    }

    public BookDTO updateByPutOneBook(BookDTO bookDTO) {
        if (!isAnyBookAttributeNull(bookDTO) && isBookIdValid(bookDTO)){
            Book savedBook = bookRepository.save(toEntityMapper.apply(bookDTO));
            return toDTOMapper.apply(savedBook);
        }
        else {
            throw new ResourceInappropriateException("Book attribute might be null");
        }
    }

    public BookDTO updateByPatchOneBook(BookDTO bookDTO) {
        if (isAnyBookAttributeNull(bookDTO) && isBookIdValid(bookDTO)){
            Book updateBook = getToUpdateBook(bookDTO);
            bookRepository.save(updateBook);
            return toDTOMapper.apply(updateBook);
        }
        else {
            throw new ResourceInappropriateException("Book attribute might not be null");
        }
    }

    public Book getToUpdateBook(BookDTO bookDTO) {
        Book savedBook = bookRepository.findById(bookDTO.id()).get();
        Book tmpBook = new Book();

        tmpBook.setId(bookDTO.id());

        if (bookDTO.title() == null || bookDTO.title().equals("null") || bookDTO.title().isBlank() || bookDTO.title().isEmpty()){
            tmpBook.setTitle(savedBook.getTitle());
        }
        else {
            tmpBook.setTitle(bookDTO.title());
        }
        if (bookDTO.author() == null || bookDTO.author().equals("null") || bookDTO.author().isBlank() || bookDTO.author().isEmpty()){
            tmpBook.setAuthor(savedBook.getAuthor());
        }
        else {
            tmpBook.setAuthor(bookDTO.author());
        }
        if (bookDTO.genre() == null || bookDTO.genre().equals("null") || bookDTO.genre().isBlank() || bookDTO.genre().isEmpty()){
            tmpBook.setGenre(savedBook.getGenre());
        }
        else {
            tmpBook.setGenre(bookDTO.genre());
        }
        if (bookDTO.publisher() == null || bookDTO.publisher().equals("null") || bookDTO.publisher().isBlank() || bookDTO.publisher().isEmpty()){
            tmpBook.setPublisher(savedBook.getPublisher());
        }
        else {
            tmpBook.setPublisher(bookDTO.publisher());
        }

        return tmpBook;
    }

    public boolean isAnyBookAttributeNull(BookDTO bookDTO){
        boolean isIdNull = Long.valueOf(bookDTO.id()).equals(0L);
        boolean isTitleNullBlankEmpty = (bookDTO.title() == null || bookDTO.title().equals("null") || bookDTO.title().isBlank() || bookDTO.title().isEmpty());
        boolean isGenreNullBlankEmpty = (bookDTO.genre() == null || bookDTO.genre().equals("null") || bookDTO.genre().isBlank() || bookDTO.genre().isEmpty());
        boolean isAuthorNullBlankEmpty = (bookDTO.author() == null || bookDTO.author().equals("null") || bookDTO.author().isBlank() || bookDTO.author().isEmpty());
        boolean isPublisherNullBlankEmpty = (bookDTO.publisher() == null || bookDTO.publisher().equals("null") || bookDTO.publisher().isBlank() || bookDTO.publisher().isEmpty());

        return (isIdNull || isTitleNullBlankEmpty || isGenreNullBlankEmpty || isAuthorNullBlankEmpty || isPublisherNullBlankEmpty);
    }

    public boolean isBookIdValid(BookDTO bookDTO){
        bookRepository.findById(bookDTO.id()).orElseThrow(() -> new ResourceNotFoundException("ID not found: " + bookDTO.id()));
        return true;
    }
}
