package de.drue.BookApp.Repository;

import de.drue.BookApp.Entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void BookRepository_Save_ReturnsSavedBook(){
        // Arrange

        // Act
        Book savedBook = bookRepository.save(book1);

        // Assert
        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook.getId()).isGreaterThan(0);
    }

    @Test
    public void BookRepository_FindAll_ReturnsMoreThanOneBook(){
        // Arrange

        // Act
        bookRepository.save(book1);
        bookRepository.save(book2);

        List<Book> bookList = bookRepository.findAll();

        //Assert
        Assertions.assertThat(bookList).isNotNull();
        Assertions.assertThat(bookList.size()).isEqualTo(2);
    }

    @Test
    public void BookRepository_FindById_ReturnsOneBookNotNull(){
        // Arrange

        // Act
        bookRepository.save(book1);

        Book savedBook = bookRepository.findById(book1.getId()).get();

        //Assert
        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook).isEqualTo(book1);
    }

    @Test
    public void BookRepository_FindById_ReturnsOneBook(){
        // Arrange

        // Act
        bookRepository.save(book1);

        Book savedBook = bookRepository.findById(book1.getId()).get();

        //Assert
        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook).isEqualTo(book1);
    }

    @Test
    public void BookRepository_FindByTitle_ReturnsOneBook(){
        // Arrange

        // Act
        bookRepository.save(book1);

        Book savedBook = bookRepository.findByTitle(book1.getTitle()).get();

        //Assert
        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook).isEqualTo(book1);
    }

    @Test
    public void BookRepository_FindByAuthor_ReturnsOneBook(){
        // Arrange

        // Act
        bookRepository.save(book1);

        Book savedBook = bookRepository.findByAuthor(book1.getAuthor()).get();

        //Assert
        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook).isEqualTo(book1);
    }

    @Test
    public void BookRepository_FindByGenre_ReturnsOneBook(){
        // Arrange

        // Act
        bookRepository.save(book1);

        Book savedBook = bookRepository.findByGenre(book1.getGenre()).get();

        //Assert
        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook).isEqualTo(book1);
    }

    @Test
    public void BookRepository_FindByPublisher_ReturnsOneBook(){
        // Arrange

        // Act
        bookRepository.save(book1);

        Book savedBook = bookRepository.findByPublisher(book1.getPublisher()).get();

        //Assert
        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook).isEqualTo(book1);
    }

    @Test
    public void BookRepository_UpdateBook_ReturnsOneBook(){
        // Arrange

        // Act
        bookRepository.save(book1);

        Book savedBook = bookRepository.findById(book1.getId()).get();
        savedBook.setAuthor("2. Author");
        savedBook.setGenre("2. Genre");

        Book updatedBook = bookRepository.save(savedBook);

        //Assert
        Assertions.assertThat(updatedBook.getAuthor()).isNotNull();
        Assertions.assertThat(updatedBook.getGenre()).isNotNull();
        Assertions.assertThat(updatedBook.getAuthor()).isEqualTo(savedBook.getAuthor());
        Assertions.assertThat(updatedBook.getGenre()).isEqualTo(savedBook.getGenre());
    }

    @Test
    public void BookRepository_DeleteBook_ReturnsOneBookIsEmprty(){
        // Arrange

        // Act
        bookRepository.save(book1);

        bookRepository.deleteById(book1.getId());

        Optional<Book> returnedBook = bookRepository.findById(book1.getId());

        //Assert
        Assertions.assertThat(returnedBook).isEmpty();
    }

}
