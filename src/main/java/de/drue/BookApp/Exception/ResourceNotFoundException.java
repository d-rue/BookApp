package de.drue.BookApp.Exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(final String message){
        super(message);
    }
}
