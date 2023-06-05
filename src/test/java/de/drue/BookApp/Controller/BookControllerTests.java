package de.drue.BookApp.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.drue.BookApp.Entity.Book;
import de.drue.BookApp.Service.BookService;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class BookControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BookService bookService;
    private Book book1;
    private Book book2;
    private Book bookId;
    private List<Book> listBooks = new ArrayList<>();

    @BeforeEach
    public void init(){
        bookId = Book.builder()
                .id(1L)
                .title("1. Titel")
                .genre("1. Genre")
                .author("1. Author")
                .publisher("1. Publisher")
                .build();

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
    public void BookController_ListAllBooks_ReturnsListBooks() throws Exception {
        // Arrange
        Mockito.when(bookService.getAllBooks()).thenReturn(listBooks);

        // Act
        ResultActions response = mockMvc.perform(get("/api/listBooks"));

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JSONArray.toJSONString(listBooks))
        );
    }

    @Test
    public void BookController_ListOneBook_ReturnsOptionalBook() throws Exception {
        // Arrange
        Mockito.when(bookService.getOneBookById(1L)).thenReturn(Optional.of(book1));

        // Act
        ResultActions response = mockMvc.perform(get("/api/book/1"));

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(book1))
                );
    }

    @Test
    public void BookController_DeleteOneBook_ReturnsOptionalLong() throws Exception {
        // Arrange
        Long id = 1L;

        Mockito.when(bookService.removeOneBook(id)).thenReturn(Optional.of(id));

        // Act
        ResultActions response = mockMvc.perform(delete("/api/book/1"));

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(id.toString())
                );
    }

    @Test
    public void BookController_PutOneBook_ReturnsOptionalBook() throws Exception {
        // Arrange
        Mockito.when(bookService.updateByPutOneBook(book1)).thenReturn(Optional.of(bookId));

        // Act
        ResultActions response = mockMvc.perform(
                put("/api/book")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(book1))
        );

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(bookId))
                );
    }

    @Test
    public void BookController_PatchOneBook_ReturnsOptionalBook() throws Exception {
        // Arrange
        Mockito.when(bookService.updateByPatchOneBook(book1)).thenReturn(Optional.of(bookId));

        // Act
        ResultActions response = mockMvc.perform(
                        patch("/api/book")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(book1))
        );

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(bookId))
                );
    }


    //                .andDo(MockMvcResultHandlers.print())
}
