package de.drue.BookApp.Controller;

import de.drue.BookApp.DTO.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import de.drue.BookApp.Service.BookService;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/listBooks",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BookDTO>> listAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{id}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> listOneBook(@PathVariable final long id){
        return new ResponseEntity<>(bookService.getOneBookById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/book/{id}",
                    method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> deleteOneBook(@PathVariable final long id){
        return new ResponseEntity<>(bookService.removeOneBook(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/book",
                    method = RequestMethod.PUT,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> putOneBook(@RequestBody final BookDTO bookDTO){
        return new ResponseEntity<>(bookService.updateByPutOneBook(bookDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/book",
                    method = RequestMethod.PATCH,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDTO> patchOneBook(@RequestBody final BookDTO bookDTO){
        return new ResponseEntity<>(bookService.updateByPatchOneBook(bookDTO), HttpStatus.OK);
    }
}
