package de.drue.BookApp.Service;

import de.drue.BookApp.DTO.BookDTO;
import de.drue.BookApp.Entity.Book;
import de.drue.BookApp.Mapper.ToDTOMapper;
import de.drue.BookApp.Mapper.ToEntityMapper;
import de.drue.BookApp.Repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {
    @Mock
    private BookRepository bookRepository;
    private BookService bookService;
    private ToDTOMapper toDTOMapper = new ToDTOMapper();
    private ToEntityMapper toEntityMapper = new ToEntityMapper();
    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookRepository, toDTOMapper, toEntityMapper);

        book = Book.builder()
                .id(1L)
                .title("A titel")
                .genre("A genre")
                .author("An author")
                .publisher("A publisher")
                .build();

        bookDTO = toDTOMapper.apply(book);
    }

    @Test
    public void bookServiceGetAllBooksReturnsListBookDTO() {
        // Arrange
        List<BookDTO> expected = List.of(toDTOMapper.apply(book));
        when(bookRepository.findAll()).thenReturn(List.of(book));

        // Act
        List<BookDTO> actual = bookService.getAllBooks();

        // Assert
        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(List.class);
        assertThat(actual.size()).isEqualTo(1);
    }

    @Test
    public void bookServiceGetOneBookByIdReturnsBookDTO(){
        // Arrange
        BookDTO expected = toDTOMapper.apply(book);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        // Act
        BookDTO actual = bookService.getOneBookById(1L);

        // Assert
        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(BookDTO.class);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void bookServiceUpdateByPutOneBookReturnsBookDTO(){
        // Arrange
        BookDTO expected = bookDTO;
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        // Act
        BookDTO actual = bookService.updateByPutOneBook(bookDTO);

        // Assert
        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(BookDTO.class);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void bookServiceUpdateByPatchOneBookReturnsBookDTO(){
        // Arrange
        book.setPublisher(null);
        BookDTO expected = toDTOMapper.apply(book);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        // Act
        BookDTO actual = bookService.updateByPatchOneBook(toDTOMapper.apply(book));

        // Assert
        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(BookDTO.class);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void bookServiceIsBookIdValidReturnsBoolean() {
        // Arrange
        boolean expected = true;
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        // Act
        Boolean actual = bookService.isBookIdValid(bookDTO);

        // Assert
        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(Boolean.class);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void bookServiceIsAnyBookAttributeNullIsNotNullReturnsBoolean() {
        // Arrange
        boolean expected = false;

        // Act
        Boolean actual = bookService.isAnyBookAttributeNull(bookDTO);

        // Assert
        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(Boolean.class);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void bookServiceIsAnyBookAttributeNullWithNullReturnsBoolean() {
        // Arrange
        book.setTitle(null);
        boolean expected = true;

        // Act
        Boolean actual = bookService.isAnyBookAttributeNull(toDTOMapper.apply(book));

        // Assert
        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(Boolean.class);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void bookServiceIsAnyBookAttributeNullWithQuotedNullReturnsBoolean() {
        // Arrange
        book.setGenre("null");
        boolean expected = true;

        // Act
        Boolean actual = bookService.isAnyBookAttributeNull(toDTOMapper.apply(book));

        // Assert
        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(Boolean.class);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void bookServiceIsAnyBookAttributeNullWithBlankReturnsBoolean() {
        // Arrange
        book.setAuthor(" ");
        boolean expected = true;

        // Act
        Boolean actual = bookService.isAnyBookAttributeNull(toDTOMapper.apply(book));

        // Assert
        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(Boolean.class);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void bookServiceIsAnyBookAttributeNullWithEmptyReturnsBoolean() {
        // Arrange
        book.setPublisher("");
        boolean expected = true;

        // Act
        Boolean actual = bookService.isAnyBookAttributeNull(toDTOMapper.apply(book));

        // Assert
        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(Boolean.class);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void bookServiceGetToUpdateBookReturnsBook() {
        // Arrange
        Book expected = book;
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        // Act
        Book actual = bookService.getToUpdateBook(toDTOMapper.apply(book));

        // Assert
        assertThat(actual).isNotNull();
        assertThat(actual).isInstanceOf(Book.class);
        assertThat(actual).isEqualTo(expected);
    }
}
