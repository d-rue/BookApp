package de.drue.BookApp.DTO;

public record BookDTO(
        long id,
        String title,
        String author,
        String genre,
        String publisher
){
}
