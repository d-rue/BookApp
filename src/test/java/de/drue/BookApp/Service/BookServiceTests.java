package de.drue.BookApp.Service;

import de.drue.BookApp.Entity.Book;
import de.drue.BookApp.Repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceTests {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void BookService_GetAllBooks_ReturnsListOfBooks(){
        // Arrange
        Book mockedBookOne = Mockito.mock(Book.class);
        Book mockedBookTwo = Mockito.mock(Book.class);

        List<Book> listBooks = List.of(mockedBookOne, mockedBookTwo);

        Mockito.when(bookRepository.findAll()).thenReturn(listBooks);

        // Act
        List<Book> callRepo = bookRepository.findAll();
        List<Book> callService = bookService.getAllBooks();

        // Assert
        Assertions.assertThat(callRepo).isEqualTo(callService);
        Assertions.assertThat(callService.size()).isEqualTo(2);
    }

    @Test
    public void BookService_IsBookIdValidByBook_ReturnsBoolean(){
        // Arrange
        Book book = Book.builder()
                .id(1L)
                .title("A titel")
                .genre("A genre")
                .author("An author")
                .publisher("A publisher")
                .build();

        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));

        // Act
        Boolean callService = bookService.isBookIdValid(book);

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService).isEqualTo(true);
    }

    @Test
    public void BookService_IsBookIdValidById_ReturnsBoolean(){
        // Arrange
        Book book = Book.builder()
                .id(1L)
                .title("A titel")
                .genre("A genre")
                .author("An author")
                .publisher("A publisher")
                .build();

        Long bookId = 1L;

        Mockito.when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // Act
        Boolean callService = bookService.isBookIdValid(bookId);

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService).isEqualTo(true);
    }

    @Test
    public void BookService_IsAnyBookAttributeNull_IsNotNull_ReturnsBoolean(){
        // Arrange
        Book book = Book.builder()
                .id(1L)
                .title("A titel")
                .genre("A genre")
                .author("An author")
                .publisher("A publisher")
                .build();

        // Act
        Boolean callService = bookService.isAnyBookAttributeNull(book);

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService).isEqualTo(false);
    }

    @Test
    public void BookService_IsAnyBookAttributeNull_WithNull_ReturnsBoolean(){
        // Arrange
        Book book = Book.builder()
                .id(1L)
                .title(null)
                .genre("A genre")
                .author("An author")
                .publisher("A publisher")
                .build();

        // Act
        Boolean callService = bookService.isAnyBookAttributeNull(book);

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService).isEqualTo(true);
    }

    @Test
    public void BookService_IsAnyBookAttributeNull_WithQuotedNull_ReturnsBoolean(){
        // Arrange
        Book book = Book.builder()
                .id(1L)
                .title("A Title")
                .genre("null")
                .author("An author")
                .publisher("A publisher")
                .build();

        // Act
        Boolean callService = bookService.isAnyBookAttributeNull(book);

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService).isEqualTo(true);
    }

    @Test
    public void BookService_IsAnyBookAttributeNull_WithBlank_ReturnsBoolean(){
        // Arrange
        Book book = Book.builder()
                .id(1L)
                .title("A Title")
                .genre("A genre")
                .author(" ")
                .publisher("A publisher")
                .build();

        // Act
        Boolean callService = bookService.isAnyBookAttributeNull(book);

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService).isEqualTo(true);
    }

    @Test
    public void BookService_IsAnyBookAttributeNull_WithEmpty_ReturnsBoolean(){
        // Arrange
        Book book = Book.builder()
                .id(1L)
                .title("A Title")
                .genre("A genre")
                .author("An author")
                .publisher("")
                .build();

        // Act
        Boolean callService = bookService.isAnyBookAttributeNull(book);

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService).isEqualTo(true);
    }

    @Test
    public void BookService_GetOneBookById_ReturnsOptionalBook(){
        // Arrange
        Book mockedBook = Mockito.mock(Book.class);

        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(mockedBook));

        // Act
        Optional<Book> callService = bookService.getOneBookById(1L);

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.get()).isEqualTo(mockedBook);
    }

    @Test
    public void BookService_GetOneBookByTitle_ReturnsOptionalBook(){
        // Arrange
        Book mockedBook = Mockito.mock(Book.class);

        Mockito.when(bookRepository.findByTitle("Title")).thenReturn(Optional.ofNullable(mockedBook));

        // Act
        Optional<Book> callService = bookService.getOneBookByTitle("Title");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.get()).isEqualTo(mockedBook);
    }

    @Test
    public void BookService_GetOneBookByAuthor_ReturnsOptionalBook(){
        // Arrange
        Book mockedBook = Mockito.mock(Book.class);

        Mockito.when(bookRepository.findByAuthor("Author")).thenReturn(Optional.ofNullable(mockedBook));

        // Act
        Optional<Book> callService = bookService.getOneBookByAuthor("Author");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.get()).isEqualTo(mockedBook);
    }

    @Test
    public void BookService_GetOneBookByGenre_ReturnsOptionalBook(){
        // Arrange
        Book mockedBook = Mockito.mock(Book.class);

        Mockito.when(bookRepository.findByGenre("Genre")).thenReturn(Optional.ofNullable(mockedBook));

        // Act
        Optional<Book> callService = bookService.getOneBookByGenre("Genre");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.get()).isEqualTo(mockedBook);
    }

    @Test
    public void BookService_GetOneBookByPublisher_ReturnsOptionalBook(){
        // Arrange
        Book mockedBook = Mockito.mock(Book.class);

        Mockito.when(bookRepository.findByPublisher("Publisher")).thenReturn(Optional.ofNullable(mockedBook));

        // Act
        Optional<Book> callService = bookService.getOneBookByPublisher("Publisher");

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService.get()).isEqualTo(mockedBook);
    }

    @Test
    public void BookService_UpdateOneBook_ReturnsBook(){
        // Arrange
        Book mockedBook = Mockito.mock(Book.class);

        Mockito.when(bookRepository.save(mockedBook)).thenReturn(mockedBook);

        // Act
        Book callService = bookService.updateOneBook(mockedBook);

        // Assert
        Assertions.assertThat(callService).isNotNull();
        Assertions.assertThat(callService).isEqualTo(mockedBook);
    }
}
