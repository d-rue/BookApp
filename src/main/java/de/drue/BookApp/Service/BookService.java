package de.drue.BookApp.Service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import de.drue.BookApp.Entity.Book;
import de.drue.BookApp.Repository.BookRepository;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getAllBooks(){
        log.info("Fetching list of books from db");
        return bookRepository.findAll();
    }

    public Optional<Book> getOneBookById(long id) {
        if (isBookIdValid(id)){
            log.info("Fetching one book from db");
            return bookRepository.findById(id);
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Book> getOneBookByTitle(String title) { return bookRepository.findByTitle(title); }

    public Optional<Book> getOneBookByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Optional<Book> getOneBookByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    public Optional<Book> getOneBookByPublisher(String publisher) {
        return bookRepository.findByPublisher(publisher);
    }

    public Optional<Long> removeOneBook(long id) {
        if (isBookIdValid(id)) {
            log.info("Delete one book from db");
            bookRepository.deleteById(id);
            return Optional.of(id);
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Book> updateByPutOneBook(Book book) {
        if (!isAnyBookAttributeNull(book) && isBookIdValid(book)){
            log.info("Update by put one book to db");
            return Optional.of(updateOneBook(book));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Book> updateByPatchOneBook(Book book) {
        if (isAnyBookAttributeNull(book) && isBookIdValid(book)){
            Book savedBook = bookRepository.findById(book.getId()).get();
            Book tmpBook = new Book();

            tmpBook.setId(book.getId());

            if (book.getTitle() == null || book.getTitle().equals("null") || book.getTitle().isBlank() || book.getTitle().isEmpty()){
                tmpBook.setTitle(savedBook.getTitle());
            }
            else {
                tmpBook.setTitle(book.getTitle());
            }
            if (book.getAuthor() == null || book.getAuthor().equals("null") || book.getAuthor().isBlank() || book.getAuthor().isEmpty()){
                tmpBook.setAuthor(savedBook.getAuthor());
            }
            else {
                tmpBook.setAuthor(book.getAuthor());
            }
            if (book.getGenre() == null || book.getGenre().equals("null") || book.getGenre().isBlank() || book.getGenre().isEmpty()){
                tmpBook.setGenre(savedBook.getGenre());
            }
            else {
                tmpBook.setGenre(book.getGenre());
            }
            if (book.getPublisher() == null || book.getPublisher().equals("null") || book.getPublisher().isBlank() || book.getPublisher().isEmpty()){
                tmpBook.setPublisher(savedBook.getPublisher());
            }
            else {
                tmpBook.setPublisher(book.getPublisher());
            }

            log.info("Update by patch one book to db");
            return Optional.of(updateOneBook(tmpBook));
        }
        else {
            return Optional.empty();
        }
    }

    public Book updateOneBook(Book book){
        return bookRepository.save(book);
    }

    public boolean isAnyBookAttributeNull(Book book){
        boolean isIdNull = Long.valueOf(book.getId()).equals(0L);
        boolean isTitleNullBlankEmpty = (book.getTitle() == null || book.getTitle().equals("null") || book.getTitle().isBlank() || book.getTitle().isEmpty());
        boolean isGenreNullBlankEmpty = (book.getGenre() == null || book.getGenre().equals("null") || book.getGenre().isBlank() || book.getGenre().isEmpty());
        boolean isAuthorNullBlankEmpty = (book.getAuthor() == null || book.getAuthor().equals("null") || book.getAuthor().isBlank() || book.getAuthor().isEmpty());
        boolean isPublisherNullBlankEmpty = (book.getPublisher() == null || book.getPublisher().equals("null") || book.getPublisher().isBlank() || book.getPublisher().isEmpty());

        return (isIdNull || isTitleNullBlankEmpty || isGenreNullBlankEmpty || isAuthorNullBlankEmpty || isPublisherNullBlankEmpty);
    }

    public boolean isBookIdValid(Book book){
        Book bookFound = bookRepository.findById(book.getId()).orElse(null);
        if (bookFound != null){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean isBookIdValid(Long id){
        Book bookFound = bookRepository.findById(id).orElse(null);
        if (bookFound != null){
            return true;
        }
        else {
            return false;
        }
    }
}
