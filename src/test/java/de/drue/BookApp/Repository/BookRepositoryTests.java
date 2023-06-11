package de.drue.BookApp.Repository;

import de.drue.BookApp.Entity.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryTests {
    @Autowired
    private BookRepository bookRepository;
    private Book book1;
    private Book book2;
    private List<Book> listBooks;

    @BeforeEach
    public void init(){
        book1 = Book.builder()
                .title("1. Titel")
                .genre("1. Genre")
                .author("1. Author")
                .publisher("1. Publisher")
                .build();

        book2 = Book.builder()
                .title("2. Titel")
                .genre("2. Genre")
                .author("2. Author")
                .publisher("2. Publisher")
                .build();

        listBooks = List.of(book1, book2);
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    public void bookRepositorySaveReturnsSavedBook(){
        // Arrange

        // Act
        Book savedBook = bookRepository.save(book1);

        // Assert
        assertThat(savedBook).isNotNull();
        assertThat(savedBook.getId()).isGreaterThan(0);
    }

    @Test
    public void bookRepositoryFindAllReturnsListBooks(){
        // Arrange

        // Act
        bookRepository.save(book1);
        bookRepository.save(book2);
        List<Book> bookList = bookRepository.findAll();

        //Assert
        assertThat(bookList).isNotNull();
        assertThat(bookList.size()).isEqualTo(2);
    }

    @Test
    public void bookRepositoryFindAllReturnsListEmpty(){
        // Arrange

        // Act
        List<Book> bookList = bookRepository.findAll();

        //Assert
        assertThat(bookList.size()).isEqualTo(0);
    }

    @Test
    public void bookRepositoryFindByIdReturnsBook(){
        // Arrange

        // Act
        bookRepository.save(book1);
        Book savedBook = bookRepository.findById(book1.getId()).get();

        //Assert
        assertThat(savedBook).isNotNull();
        assertThat(savedBook).isEqualTo(book1);
    }

    @Test
    public void bookRepositoryFindByIdReturnsOptionalEmpty(){
        // Arrange

        // Act
        Optional<Book> savedBook = bookRepository.findById(book1.getId());

        //Assert
        assertThat(savedBook).isEqualTo(Optional.empty());
    }

    @Test
    public void bookRepositoryUpdateOneBookReturnsBook(){
        // Arrange

        // Act
        bookRepository.save(book1);
        Book toUpdateBook = bookRepository.findById(book1.getId()).get();
        toUpdateBook.setAuthor("2. Author");
        toUpdateBook.setGenre("2. Genre");
        Book updatedBook = bookRepository.save(toUpdateBook);

        //Assert
        assertThat(updatedBook.getAuthor()).isNotNull();
        assertThat(updatedBook.getGenre()).isNotNull();
        assertThat(updatedBook.getAuthor()).isEqualTo(toUpdateBook.getAuthor());
        assertThat(updatedBook.getGenre()).isEqualTo(toUpdateBook.getGenre());
    }

    @Test
    public void bookRepositoryDeleteBookReturnsBookEmpty(){
        // Arrange

        // Act
        bookRepository.save(book1);
        bookRepository.deleteById(book1.getId());
        Optional<Book> returnedBook = bookRepository.findById(book1.getId());

        //Assert
        assertThat(returnedBook).isEmpty();
    }

}
