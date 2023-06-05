package de.drue.BookApp.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import de.drue.BookApp.Service.BookService;
import de.drue.BookApp.Entity.Book;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/listBooks",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Book>> listAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Book>> listOneBook(@PathVariable long id){
        Optional<Book> book = bookService.getOneBookById(id);

        HttpStatus httpStatus;
        if (book.isPresent()){
            httpStatus = HttpStatus.OK;
        }
        else{
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(book, httpStatus);
    }

    @RequestMapping(value = "/book/{id}",
                    method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Long>> deleteOneBook(@PathVariable long id){
        Optional<Long> bookId = bookService.removeOneBook(id);

        HttpStatus httpStatus;
        if (bookId.isPresent()){
            httpStatus = HttpStatus.OK;
        }
        else{
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(bookId, httpStatus);
    }

    @RequestMapping(value = "/book",
                    method = RequestMethod.PUT,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Book>> putOneBook(@RequestBody Book book){

        System.out.println(book);
        Optional<Book> reBook = bookService.updateByPutOneBook(book);

        HttpStatus httpStatus;
        if (reBook.isPresent()){
            httpStatus = HttpStatus.OK;
        }
        else{
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(reBook, httpStatus);
    }

    @RequestMapping(value = "/book",
                    method = RequestMethod.PATCH,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Optional<Book>> patchOneBook(@RequestBody Book book){
        Optional<Book> reBook = bookService.updateByPatchOneBook(book);

        HttpStatus httpStatus;
        if (reBook.isPresent()){
            httpStatus = HttpStatus.OK;
        }
        else{
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(reBook, httpStatus);
    }
}
