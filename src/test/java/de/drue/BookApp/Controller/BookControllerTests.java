package de.drue.BookApp.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.drue.BookApp.DTO.BookDTO;
import de.drue.BookApp.Entity.Book;
import de.drue.BookApp.Mapper.ToDTOMapper;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
    private ToDTOMapper toDTOMapper = new ToDTOMapper();
    private Book book;
    private BookDTO bookDTO;
    private List<Book> listBook = new ArrayList<>();
    private List<BookDTO> listBookDTO = new ArrayList<>();

    @BeforeEach
    public void init(){
        book = Book.builder()
                .id(1L)
                .title("1. Titel")
                .genre("1. Genre")
                .author("1. Author")
                .publisher("1. Publisher")
                .build();

        bookDTO = toDTOMapper.apply(book);
    }

    @Test
    public void bookControllerListAllBooksReturnsListBookDTO() throws Exception {
        // Arrange
        listBook = List.of(book);
        listBookDTO = List.of(bookDTO);
        when(bookService.getAllBooks()).thenReturn(listBookDTO);

        // Act
        ResultActions response = mockMvc.perform(get("/api/listBooks"));

        // Assert
        response.andExpect(status().isOk())
                .andExpect(content().json(JSONArray.toJSONString(listBook))
        );
    }

    @Test
    public void bookControllerListBookReturnsBookDTO() throws Exception {
        // Arrange
        when(bookService.getOneBookById(1L)).thenReturn(bookDTO);

        // Act
        ResultActions response = mockMvc.perform(get("/api/book/1"));

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(book))
                );
    }

    @Test
    public void bookControllerDeleteOneBookReturnsLong() throws Exception {
        // Arrange
        long id = 1L;
        when(bookService.removeOneBook(id)).thenReturn(id);

        // Act
        ResultActions response = mockMvc.perform(delete("/api/book/1"));

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(Long.toString(id))
                );
    }

    @Test
    public void bookControllerPutOneBookReturnsBookDTO() throws Exception {
        // Arrange
        when(bookService.updateByPutOneBook(bookDTO)).thenReturn(bookDTO);

        // Act
        ResultActions response = mockMvc.perform(
                put("/api/book")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(book))
        );

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(book))
                );
    }

    @Test
    public void bookControllerPatchOneBookReturnsBookDTO() throws Exception {
        // Arrange
        when(bookService.updateByPatchOneBook(bookDTO)).thenReturn(bookDTO);

        // Act
        ResultActions response = mockMvc.perform(
                        patch("/api/book")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(book))
        );

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(book))
                );
    }

    @Test
    public void bookControllerPostOneBookReturnsBookDTO() throws Exception {
        // Arrange
        when(bookService.createOneBook(bookDTO)).thenReturn(bookDTO);

        // Act
        ResultActions response = mockMvc.perform(
                post("/api/book")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(book))
        );

        // Assert
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writer().withDefaultPrettyPrinter().writeValueAsString(book))
                );
    }
}
